package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class socio_menu extends AppCompatActivity {
    Button btnBarco, btnViajes,btnPatrones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socio_menu);
        btnBarco=(Button) findViewById(R.id.btnSocioBarco);
        btnBarco.setOnClickListener(this::onClick);
        btnViajes = findViewById(R.id.btnSocioViajes);
        btnViajes.setOnClickListener(this::onClick);
        btnPatrones = findViewById(R.id.btnSocioPatrones);
        btnPatrones.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnSocioBarco){
            Intent intent= new Intent(socio_menu.this, socios_barcos_crud.class);
            startActivity(intent);
        }
        if(id==R.id.btnSocioViajes){
            Intent intent= new Intent(socio_menu.this, socios_viajes_crud.class);
            startActivity(intent);
        }
        if(id==R.id.btnSocioPatrones){
            Intent intent= new Intent(socio_menu.this, socios_patrones_crud.class);
            startActivity(intent);
        }
    }
}