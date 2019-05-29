package com.example.proyecto_apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Mundo.Curso;
import Mundo.FirebaseConexion;
import Mundo.Profesores;

public class PerfilCursoActivity extends AppCompatActivity {

    private EditText txtId,txtProfesores,txtEstudiantes;
    private String id,profesores;
    private Button asignar;
    private FirebaseConexion fbCon;
    private String intent;

    DatabaseReference databaseProfesores;
    DatabaseReference databaseCursos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_curso);

        txtId = (EditText)findViewById(R.id.txtId);
        txtProfesores = (EditText)findViewById(R.id.txtProfesores);


        asignar = (Button)findViewById(R.id.btnAsignar);

        intent = getIntent().getStringExtra("ListViewClickedValue");
        txtId.setText(intent);
        fbCon = new FirebaseConexion(this);

        init();
    }


    private void init(){

        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            saveProfesor();

            }
        });
    }

    private void saveProfesor(){
        id = txtId.getText().toString();
        profesores = txtProfesores.getText().toString();

        databaseProfesores = FirebaseDatabase.getInstance().getReference("Cursos").child(id);
        databaseCursos = FirebaseDatabase.getInstance().getReference("Profesores").child(profesores);

            String id = databaseProfesores.push().getKey();
            String idC = databaseCursos.push().getKey();
            Curso curso = new Curso(idC);
            Profesores profesores = new Profesores(id);
            databaseProfesores.child(id).setValue(profesores);
            databaseCursos.child(idC).setValue(curso);
            Toast.makeText(this, "Profesor guardado", Toast.LENGTH_LONG).show();



    }




}
