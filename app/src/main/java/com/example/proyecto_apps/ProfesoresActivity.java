package com.example.proyecto_apps;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Mundo.Curso;
import Mundo.FirebaseConexion;
import Mundo.Profesores;

public class ProfesoresActivity extends AppCompatActivity {

    private EditText txtCedula,txtNombre,txtApellido,txtFacultad,txtMateria,txtPrograma,txtRol,txtUsuario,txtContrasenia;
    private String cedula,nombre,apellido,facultad,materia,programa,rol,usuario,contrasenia;
    private Button agregar,consultar,modificar,desactivar;
    private FirebaseConexion fbCon;
    private Spinner spnRol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        txtCedula = (EditText)findViewById(R.id.txtCedula);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtFacultad = (EditText)findViewById(R.id.txtFacultad);
        txtMateria = (EditText)findViewById(R.id.txtMateria);
        txtPrograma = (EditText)findViewById(R.id.txtPrograma);

        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtContrasenia = (EditText)findViewById(R.id.txtContrasenia);

        agregar = (Button)findViewById(R.id.btnAgregar);
        consultar = (Button)findViewById(R.id.btnConsultar);
        modificar = (Button)findViewById(R.id.btnModificar);
        desactivar = (Button)findViewById(R.id.btnDesactivar);

        spnRol = (Spinner)findViewById(R.id.spnRol);

        fbCon = new FirebaseConexion(this);

        init();
    }


    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    private void init() {

        FirebaseDatabase.getInstance().getReference("Roles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> roles = new ArrayList<>();

                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    String rolNombre = areaSnapshot.child("nombre").getValue(String.class);
                    roles.add(rolNombre);
                }

                Spinner areaSpinner = (Spinner) findViewById(R.id.spnRol);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(ProfesoresActivity.this, android.R.layout.simple_spinner_item, roles);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                areaSpinner.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cedula = txtCedula.getText().toString();
                nombre = txtNombre.getText().toString();
                apellido = txtApellido.getText().toString();
                facultad = txtFacultad.getText().toString();
                materia = txtMateria.getText().toString();
                programa = txtPrograma.getText().toString();
                rol = spnRol.getSelectedItem().toString();
                usuario = txtUsuario.getText().toString();
                contrasenia = txtContrasenia.getText().toString();


                Profesores p = new Profesores(cedula,nombre,apellido,facultad,materia,programa,rol,usuario,contrasenia);
                fbCon.crearProfesor(p);
                Toast.makeText(getApplicationContext(), "Profesor Agregado",
                        Toast.LENGTH_SHORT).show();

                txtCedula.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtFacultad.setText("");
                txtMateria.setText("");
                txtPrograma.setText("");
                txtUsuario.setText("");



            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cedula = txtCedula.getText().toString();

                final String idCompare = cedula;





                FirebaseDatabase.getInstance().getReference("Profesores").getRef().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            String idActual = postSnapshot.child("cedula").getValue().toString();

                            if(idActual.compareTo(idCompare) == 0){

                                txtNombre.setText(postSnapshot.child("nombre").getValue().toString());


                                txtApellido.setText(postSnapshot.child("apellido").getValue().toString());


                                txtFacultad.setText(postSnapshot.child("facultad").getValue().toString());

                                txtMateria.setText(postSnapshot.child("materia").getValue().toString());

                                txtPrograma.setText(postSnapshot.child("programa").getValue().toString());

                                spnRol.setSelection(getIndex(spnRol, postSnapshot.child("rolNombre").getValue().toString()));


                                txtUsuario.setText(postSnapshot.child("usuario").getValue().toString());

                                txtContrasenia.setText(postSnapshot.child("contrasenia").getValue().toString());




                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
            }
        });

        desactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cedula = txtCedula.getText().toString();
                Profesores p = new Profesores("","","","","","","","","",false);
                p.setActivo(false);

                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Profesores");
                DatabaseReference currentUserBD = mDatabase.child(cedula);
                currentUserBD.setValue(p);
                Toast.makeText(getApplicationContext(), "Profesor Desactivado",
                        Toast.LENGTH_SHORT).show();


            }

        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cedula = txtCedula.getText().toString();


                Profesores p = new Profesores("","","","","","","","","");


                p.setNombre(txtNombre.getText().toString());
                p.setApellido(txtApellido.getText().toString());
                p.setFacultad(txtFacultad.getText().toString());
                p.setMateria(txtMateria.getText().toString());
                p.setPrograma(txtPrograma.getText().toString());
                p.setUsuario(txtUsuario.getText().toString());
                p.setContrasenia(txtContrasenia.getText().toString());



                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Profesores");
                DatabaseReference currentUserBD = mDatabase.child(cedula);
                currentUserBD.setValue(p);

                Toast.makeText(getApplicationContext(), "Profesor Actualizado",
                        Toast.LENGTH_SHORT).show();


            }

        });


    }




}
