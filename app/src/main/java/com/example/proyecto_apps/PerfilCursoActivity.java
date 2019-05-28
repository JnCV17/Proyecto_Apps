package com.example.proyecto_apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Mundo.Curso;
import Mundo.FirebaseConexion;
import Mundo.Profesores;

public class PerfilCursoActivity extends AppCompatActivity {

    private EditText txtId,txtProfesores,txtEstudiantes;
    private String id,profesores,estudiantes;
    private Button asignar;
    private FirebaseConexion fbCon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_curso);

        txtId = (EditText)findViewById(R.id.txtId);
        txtEstudiantes = (EditText)findViewById(R.id.txtProfesores);
        txtProfesores = (EditText)findViewById(R.id.txtEstudiantes);
        asignar = (Button)findViewById(R.id.btnAsignar);

        String text = getIntent().getStringExtra("ListViewClickedValue");
        txtId.setText(text);
        fbCon = new FirebaseConexion(this);

        init();
    }


    private void init(){

        asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = txtId.getText().toString();
                profesores = txtProfesores.getText().toString();
                estudiantes = txtEstudiantes.getText().toString();

                final String idCompare = profesores;

                FirebaseDatabase.getInstance().getReference("Profesores").getRef().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            String idActual = postSnapshot.child("cedula").getValue().toString();

                            if(idActual.compareTo(idCompare) == 0){

                               String nombre = postSnapshot.child("nombre").getValue().toString();


                               String apellido = postSnapshot.child("apellido").getValue().toString();


                               String facultad =  postSnapshot.child("facultad").getValue().toString();

                               String materia = postSnapshot.child("materia").getValue().toString();

                               String programa = postSnapshot.child("programa").getValue().toString();

                               String rol =  postSnapshot.child("rolNombre").getValue().toString();

                               String usuario = postSnapshot.child("usuario").getValue().toString();

                               String contrasenia = postSnapshot.child("contrasenia").getValue().toString();

                                final Profesores p = new Profesores(profesores,nombre,apellido,facultad,materia,programa,rol,usuario,contrasenia);
                               fbCon.asignarUsuario(p,id);
                                Toast.makeText(getApplicationContext(), "Profesor Agregado",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

            }
        });



    }
}
