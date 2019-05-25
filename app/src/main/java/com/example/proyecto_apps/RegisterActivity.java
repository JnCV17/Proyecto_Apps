package com.example.proyecto_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import Mundo.FirebaseConexion;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseConexion fbCon;
    private Button btnRegistro;
    private String email,password;
    private EditText txtEmail,txtContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fbCon = new FirebaseConexion(this);
        btnRegistro = (Button)findViewById(R.id.btnRegistro);
        txtEmail =(EditText)findViewById(R.id.txtEmail);
        txtContraseña =(EditText)findViewById(R.id.txtContraseña);

        init();


    }

    private void init(){
        btnRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                password = txtContraseña.getText().toString();
                fbCon.createAccount(email, password);
            }
        });

        TextView linkSignUp = (TextView) findViewById(R.id.lblCuenta);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }



}
