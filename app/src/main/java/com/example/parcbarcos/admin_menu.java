package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_menu extends AppCompatActivity {
    Button adminSocios, adminPatrones, adminViajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        adminSocios=(Button) findViewById(R.id.btnAdminSocios);
        adminSocios.setOnClickListener(this::onClick);

        adminPatrones= (Button) findViewById(R.id.btnAdminPatrones);
        adminPatrones.setOnClickListener(this::onClick);

        adminViajes= (Button) findViewById(R.id.btnAdminViajes);
        adminViajes.setOnClickListener(this::onClick);

    }

    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnAdminSocios){
            Intent intent= new Intent(admin_menu.this, admin_socios_crud.class);
            startActivity(intent);
        }
        if(id==R.id.btnAdminPatrones){
            Intent intent = new Intent(admin_menu.this, admin_patrones_crud.class);
            startActivity(intent);
        }
        if(id==R.id.btnAdminViajes){
            Intent intent = new Intent(admin_menu.this, admin_viajes_crud.class);
            startActivity(intent);
        }
    }
}