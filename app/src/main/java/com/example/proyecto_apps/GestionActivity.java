package com.example.proyecto_apps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class GestionActivity extends AppCompatActivity {

    public Button btnCursos,btnProfesores,btnEstudiantes,btnCurriculum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        btnCursos = (Button)findViewById(R.id.btnCursos);
        btnEstudiantes = (Button)findViewById(R.id.btnEstudiantes);
        btnProfesores = (Button)findViewById(R.id.btnProfesores);
        btnCurriculum = (Button)findViewById(R.id.btnCurriculum);
        init();
    }

    private void init(){

        btnCursos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                                    Intent intent = new Intent(GestionActivity.this, CursosActivity.class);
                                    startActivity(intent);

                            }
                        });

        btnProfesores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GestionActivity.this, ProfesoresActivity.class);
                startActivity(intent);

            }
        });
        btnEstudiantes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GestionActivity.this, CursosActivity.class);
                startActivity(intent);

            }
        });

       btnCurriculum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GestionActivity.this, CurriculumActivity.class);
                startActivity(intent);

            }
        });
            }

}




