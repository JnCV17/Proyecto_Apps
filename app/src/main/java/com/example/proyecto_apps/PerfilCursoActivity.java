package com.example.proyecto_apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class PerfilCursoActivity extends AppCompatActivity {

    private EditText txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_curso);

        txtId = (EditText)findViewById(R.id.txtId);

        String text = getIntent().getStringExtra("ListViewClickedValue");

        txtId.setText(text);
    }
}
