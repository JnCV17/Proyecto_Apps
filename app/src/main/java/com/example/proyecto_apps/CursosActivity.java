package com.example.proyecto_apps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

import Mundo.Curso;
import Mundo.FirebaseConexion;

public class CursosActivity extends AppCompatActivity {

    private EditText txtId,txtNombre,txtDescripcion,dateInicio,dateFinal,txtHorario,dateParciales,txtNovedad;
    private String id,nombre,descripcion,fechaInicial,fechaFinal,horario,fechaParcial,novedad;
    private Button crear,actualizar,consultar,eliminar;
    private FirebaseConexion fbCon;
    private static final String TAG = "CursosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        txtId = (EditText)findViewById(R.id.txtId);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtDescripcion = (EditText)findViewById(R.id.txtDescripcion);
        dateInicio = (EditText)findViewById(R.id.dateInicio);
        dateFinal = (EditText)findViewById(R.id.dateFinal);
        txtHorario = (EditText)findViewById(R.id.txtHorario);
        dateParciales = (EditText)findViewById(R.id.dateParciales);
        txtNovedad = (EditText)findViewById(R.id.txtNovedad);
        crear = (Button)findViewById(R.id.btnCrear) ;
        actualizar = (Button)findViewById(R.id.btnActualizar) ;
        consultar = (Button)findViewById(R.id.btnConsultar) ;
        eliminar = (Button)findViewById(R.id.btnEliminar) ;

        fbCon = new FirebaseConexion(this);

        init();


    }

    private void init() {

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = txtId.getText().toString();
                nombre = txtNombre.getText().toString();
                descripcion = txtDescripcion.getText().toString();
                fechaInicial = dateInicio.getText().toString();
                fechaFinal = dateFinal.getText().toString();
                horario = txtHorario.getText().toString();
                fechaParcial = dateParciales.getText().toString();
                novedad = txtNovedad.getText().toString();

                Curso c = new Curso( id, nombre, descripcion,fechaInicial, fechaFinal, horario, fechaParcial, novedad);
                fbCon.crearCurso(c);
                Toast.makeText(getApplicationContext(), "Curso Agregado",
                        Toast.LENGTH_SHORT).show();

                txtId.setText("");
                txtNombre.setText("");
                txtDescripcion.setText("");
                txtHorario.setText("");
                dateInicio.setText("");
                dateFinal.setText("");
                dateParciales.setText("");
                txtNovedad.setText("");


            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = txtId.getText().toString();

                final String idCompare = id;

                final String[] valores = new String[8];

                FirebaseDatabase.getInstance().getReference("Cursos").getRef().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            String idActual = postSnapshot.child("id").getValue().toString();

                            if(idActual.compareTo(idCompare) == 0){
                                valores[0] = postSnapshot.child("id").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("id").getValue());

                                valores[1] = postSnapshot.child("nombre").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("nombre").getValue());
                                txtNombre.setText(postSnapshot.child("nombre").getValue().toString());

                                valores[2] = postSnapshot.child("descripcion").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("descripcion").getValue());
                                txtDescripcion.setText(postSnapshot.child("descripcion").getValue().toString());

                                valores[3] = postSnapshot.child("fechaInicio").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("fechaInicio").getValue());
                                dateInicio.setText(postSnapshot.child("fechaInicio").getValue().toString());

                                valores[4] = postSnapshot.child("fechaFinal").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("fechaFinal").getValue());
                                dateFinal.setText(postSnapshot.child("fechaFinal").getValue().toString());

                                valores[5] = postSnapshot.child("horario").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("horario").getValue());
                                txtHorario.setText(postSnapshot.child("horario").getValue().toString());

                                valores[6] = postSnapshot.child("fechaParciales").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("fechaParciales").getValue());
                                dateParciales.setText(postSnapshot.child("fechaParciales").getValue().toString());

                                valores[7] = postSnapshot.child("novedades").getValue().toString();
                                Log.e(TAG, "======="+postSnapshot.child("novedades").getValue());
                                txtNovedad.setText(postSnapshot.child("novedades").getValue().toString());

                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.e(TAG, "Failed to read app title value.", error.toException());
                    }
                });
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                id = txtId.getText().toString();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Cursos");
                DatabaseReference currentUserBD = mDatabase.child(id);
                currentUserBD.removeValue();
                Toast.makeText(getApplicationContext(), "Curso Eliminado",
                        Toast.LENGTH_SHORT).show();


            }

        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = txtId.getText().toString();


                Curso c = new Curso( "","","","","", "", "", "");

                c.setNombre(txtNombre.getText().toString());
                c.setDescripcion(txtDescripcion.getText().toString());
                c.setFechaInicio(dateInicio.getText().toString());
                c.setFechaFinal(dateFinal.getText().toString());
                c.setHorario(txtHorario.getText().toString());
                c.setFechaParciales(dateParciales.getText().toString());
                c.setNovedades(txtNovedad.getText().toString());

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Cursos");
                DatabaseReference currentUserBD = mDatabase.child(id);
                currentUserBD.setValue(c);

                Toast.makeText(getApplicationContext(), "Curso Actualizado",
                        Toast.LENGTH_SHORT).show();


            }

        });


    }

}