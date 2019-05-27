package com.example.proyecto_apps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Mundo.Curso;

public class CurriculumActivity extends AppCompatActivity {

    private List<Curso> listCursos = new ArrayList<Curso>();
    ArrayAdapter<Curso> arrayAdapterCurso;

    private ListView listV_cursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);

        listV_cursos = findViewById(R.id.lstCursos);

        listarDatos();

    }


    private void listarDatos() {
        FirebaseDatabase.getInstance().getReference().child("Cursos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCursos.clear();

                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {
                    Curso p = objSnaptshot.getValue(Curso.class);
                    listCursos.add(p);

                    arrayAdapterCurso = new ArrayAdapter<Curso>(CurriculumActivity.this, android.R.layout.simple_list_item_1, listCursos);
                    listV_cursos.setAdapter(arrayAdapterCurso);

                    listV_cursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Object o = arrayAdapterCurso.getItem(position).getId();
                            String item = o.toString();

                            Intent intent = new Intent(CurriculumActivity.this, PerfilCursoActivity.class);

                            // Sending value to another activity using intent.
                            intent.putExtra("ListViewClickedValue", item);

                            startActivity(intent);



                    }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        }
    }

















