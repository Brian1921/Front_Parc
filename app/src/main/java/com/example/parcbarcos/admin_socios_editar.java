package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class admin_socios_editar extends AppCompatActivity {

    EditText editNom_admin_update_socio, editApe_admin_update_socio, editTel_admin_update_socio, editEm_admin_update_socio;
    Button btnEditar_admin_socio;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_socios_editar);/*
        editNom_admin_update_socio = findViewById(R.id);
        editApe_admin_update_socio = findViewById(R.id);
        editTel_admin_update_socio = findViewById(R.id);
        editEm_admin_update_socio = findViewById(R.id);*/

    }
}