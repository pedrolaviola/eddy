package com.pedrolaviola.user.eddy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrolaviola.user.eddy.models.Feed;
import com.pedrolaviola.user.eddy.models.Usuario;

import java.util.Calendar;

public class NewFeedActivity extends AppCompatActivity {
    private EditText titulo,desc;
    private TextView data;
    private Button btn_criar,btn_back;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Long FeedCount;
    private int myear, mmonth,mday;
    static final int DIALOG_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);
        final Calendar cal = Calendar.getInstance();
        myear = cal.get(Calendar.YEAR);
        mmonth = cal.get(Calendar.MONTH);
        mday = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnClick();
        titulo = (EditText)findViewById(R.id.edit_title);
        desc = (EditText)findViewById(R.id.edit_descricao);
        btn_criar = (Button)findViewById(R.id.btn_criarNota);
        btn_back = (Button)findViewById(R.id.btn_back_newID);

        inicializarFirebase();
        eventoGetFeedCount();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titulo.getText()!=null&&data.getText()!=null&&desc.getText()!=null){
                    Feed feed = new Feed();
                    feed.setTitulo(titulo.getText().toString());
                    feed.setData(data.getText().toString());
                    feed.setDescricao(desc.getText().toString());
                    String count = String.valueOf(FeedCount+1);
                    feed.setId(Integer.parseInt(count));
                    databaseReference.child("Edificio").child("1000").child("Feed").child(count).setValue(feed);
                    eventoSetFeedCount(FeedCount+1);
                    startActivity(new Intent(NewFeedActivity.this, HomeActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(NewFeedActivity.this,"Preencha todos os campos",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    public void showDialogOnClick(){
        data = (TextView)findViewById(R.id.edit_data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id==DIALOG_ID){
            return new DatePickerDialog(this,dpickerListener,myear,mmonth,mday);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myear = i;
            mmonth = i1;
            mday = i2;
            String date = mday+"/"+mmonth+"/"+myear;
            data.setText(date);
        }
    };
    public void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    public  void eventoGetFeedCount(){
        databaseReference.child("Edificio").child("1000").child("FeedCount").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Long s = dataSnapshot.getValue(Long.class);
                FeedCount = s;


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public  void eventoSetFeedCount(Long count){

        databaseReference.child("Edificio").child("1000").child("FeedCount").setValue(count);
    }
}
