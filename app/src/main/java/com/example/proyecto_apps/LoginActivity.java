package com.example.proyecto_apps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Mundo.FirebaseConexion;

public class LoginActivity extends AppCompatActivity {

    private Button btnIngresar;
    private EditText txtEmail,txtContraseña;
    private String email,password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        txtEmail =(EditText)findViewById(R.id.txtEmail);
        txtContraseña =(EditText)findViewById(R.id.txtContraseña);
        mAuth = FirebaseAuth.getInstance();

        init();

    }

    private void init(){

       btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                password = txtContraseña.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                FirebaseUser user = mAuth.getCurrentUser();

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {


                                    Toast.makeText(LoginActivity.this, "Authentication Failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Intent intent = new Intent(LoginActivity.this, GestionActivity.class);
                                    startActivity(intent);
                                }

                                // ...
                            }
                        });
            }
        });

        TextView linkSignUp = (TextView) findViewById(R.id.lblCrear);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}
