package com.example.proyecto_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button btnIngresar;
    private EditText txtEmail,txtContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        txtEmail =(EditText)findViewById(R.id.txtEmail);
        txtContraseña =(EditText)findViewById(R.id.txtContraseña);

        init();

    }

    private void init(){
       /* btnIngresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                password = txtContraseña.getText().toString();
                fbCon.createAccount(email, password);
            }
        });
        */

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
