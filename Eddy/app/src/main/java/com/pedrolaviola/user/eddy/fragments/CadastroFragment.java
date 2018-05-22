package com.pedrolaviola.user.eddy.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pedrolaviola.user.eddy.HomeActivity;
import com.pedrolaviola.user.eddy.LoginActivity;
import com.pedrolaviola.user.eddy.R;
import com.pedrolaviola.user.eddy.models.Usuario;

public class CadastroFragment extends Fragment {

    private EditText edt_codEdi, edt_numApto,edt_nome;
    private Button btn_cadastrar;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        edt_codEdi = (EditText)view.findViewById(R.id.edt_codEdiID);
        edt_numApto = (EditText) view.findViewById(R.id.edt_numAptoID);
        edt_nome = (EditText) view.findViewById(R.id.edt_nomeID);
        btn_cadastrar = (Button) view.findViewById(R.id.btn_codEdiID);
        auth = FirebaseAuth.getInstance();

        inicializarFirebase();

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_numApto.getText()!=null&&edt_codEdi.getText()!=null&&edt_nome.getText()!=null){
                    Usuario usuario = new Usuario();
                    usuario.setApartamento(edt_numApto.getText().toString());
                    usuario.setCodEdi(edt_codEdi.getText().toString());
                    usuario.setNome(edt_nome.getText().toString());
                    databaseReference.child("Usuario").child(auth.getCurrentUser().getUid()).setValue(usuario);
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
                else{
                    Toast.makeText(getContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void inicializarFirebase(){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
