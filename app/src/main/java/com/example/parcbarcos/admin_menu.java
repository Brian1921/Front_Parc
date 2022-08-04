package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_menu extends AppCompatActivity {
    Button adminSocios, adminPatrones, adminViajes, adminCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        adminSocios=(Button) findViewById(R.id.btnAdminSocios);
        adminSocios.setOnClickListener(this::onClick);

        adminPatrones = findViewById(R.id.btnAdminPat);
        adminPatrones.setOnClickListener(this::onClick);

        adminViajes= (Button) findViewById(R.id.btnAdminViajes);
        adminViajes.setOnClickListener(this::onClick);

        adminCerrarSesion= (Button) findViewById(R.id.btnAdminSalir);
        adminCerrarSesion.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnAdminSocios){
            Intent intent= new Intent(admin_menu.this, admin_socios_crud.class);
            startActivity(intent);
        }
        if(id==R.id.btnAdminViajes){
            Intent intent = new Intent(admin_menu.this, admin_viajes_crud.class);
            startActivity(intent);
        }
        if(id==R.id.btnAdminSalir){
            Intent intent = new Intent(admin_menu.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
        if(id==R.id.btnAdminPat){
            Intent intent = new Intent(admin_menu.this, admin_patrones_crud.class);
            startActivity(intent);
        }
    }
}