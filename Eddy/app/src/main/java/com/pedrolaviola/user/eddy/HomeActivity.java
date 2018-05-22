package com.pedrolaviola.user.eddy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedrolaviola.user.eddy.Adapters.FeedAdapter;
import com.pedrolaviola.user.eddy.models.Feed;
import com.pedrolaviola.user.eddy.models.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private TextView txt_reuniao;
    private TextView txt_dataReuniao;
    private ListView lista_feed;
    private List<Feed> lista;
    private FeedAdapter adapter;
    private Toolbar myToolbar;
    private int myear, mmonth, mday;
    static final int DIALOG_ID = 0;
    private ImageView btn_meeting;
    private Long FeedCount;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myToolbar = (Toolbar) findViewById(R.id.myToolbarID);
        setSupportActionBar(myToolbar);
        final Calendar cal = Calendar.getInstance();
        myear = cal.get(Calendar.YEAR);
        mmonth = cal.get(Calendar.MONTH);
        mday = cal.get(Calendar.DAY_OF_MONTH);

        txt_dataReuniao = (TextView) findViewById(R.id.txt_dataReuniaoID);
        txt_reuniao = (TextView) findViewById(R.id.txt_reuniaoID);
        lista_feed = (ListView) findViewById(R.id.feed_listViewID);
        lista = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        inicializarFirebase();
        showDialogOnClick();
//        eventoUsuario();
        eventoNomeEdi();
        eventoFeed();
        eventoMeeting();
        lista_feed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Feed f = lista.get(i);
                Intent intent = new Intent(HomeActivity.this, FeedDetailActivity.class);
                intent.putExtra("Feed", f);
                startActivity(intent);
            }
        });
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fab_addID);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NewFeedActivity.class);
                startActivity(intent);

            }
        });
        lista_feed.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int aux = lista.get(i).getId();
                FeedCount = (long) aux;
                String feedID = String.valueOf(aux);
                databaseReference.child("Edificio").child("1000").child("Feed").child(feedID).removeValue();
                eventoSetFeedCount(FeedCount - 1);
                lista.clear();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        auth.signOut();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void showDialogOnClick() {
        btn_meeting = (ImageView) findViewById(R.id.myImageView);
        btn_meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListener, myear, mmonth, mday);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            myear = i;
            mmonth = i1 + 1;
            mday = i2;
            String data = mday + "/" + mmonth + "/" + myear;
            databaseReference.child("Edificio").child("1000").child("meeting").child("data").setValue(data);
        }
    };

    private void eventoFeed() {
        databaseReference.child("Edificio").child("1000").child("Feed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objdata : dataSnapshot.getChildren()) {
                    Feed f = objdata.getValue(Feed.class);
                    lista.add(f);

                }
                adapter = new FeedAdapter(getApplicationContext(), lista);
                lista_feed.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void eventoMeeting() {
        databaseReference.child("Edificio").child("1000").child("meeting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Meeting m = dataSnapshot.getValue(Meeting.class);
                if (m.isMarcada()) {
                    txt_reuniao.setText("Reunião marcada para o dia");
                    txt_dataReuniao.setText(m.getData());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //    void eventoUsuario(){
//        databaseReference.child("Usuario").child("aux").child("codEdi").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                codEdi = dataSnapshot.child("codEdi").getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
    public void eventoNomeEdi() {
        databaseReference.child("Edificio").child("1000").child("nomEdi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String m = dataSnapshot.getValue(String.class);
                getSupportActionBar().setTitle(m);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void eventoGetFeedCount() {
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

    public void eventoSetFeedCount(Long count) {

        databaseReference.child("Edificio").child("1000").child("FeedCount").setValue(count);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
