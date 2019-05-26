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

import java.util.UUID;

import Mundo.Curso;
import Mundo.FirebaseConexion;

public class CursosActivity extends AppCompatActivity {

    private EditText txtId,txtNombre,txtDescripcion,dateInicio,dateFinal,txtHorario,dateParciales,txtNovedad;
    private String id,nombre,descripcion,fechaInicial,fechaFinal,horario,fechaParcial,novedad;
    private Button crear,actualizar,consultar,eliminar;
    private FirebaseConexion fbCon;

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


            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = txtId.getText().toString();

                Curso consulta = fbCon.consultarCurso(id);

                txtNombre.setText(consulta.getNombre());
                txtDescripcion.setText(consulta.getDescripcion());
                dateInicio.setText(consulta.getFechaInicio());
                dateFinal.setText(consulta.getFechaFinal());
                txtHorario.setText(consulta.getHorario());
                dateParciales.setText(consulta.getFechaParciales());
                txtNovedad.setText(consulta.getNovedades());
            }
        });
    }

}