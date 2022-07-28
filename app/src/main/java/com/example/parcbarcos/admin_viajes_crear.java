package com.example.parcbarcos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class admin_viajes_crear extends AppCompatActivity {
    EditText editId_admin_isert_viajes, editDestino_admin_isert_viajes,editId_barco_admin_insert_viajes,
            edit_Id_patron_admin_insert_viajes;

    DatePicker editFS_admin_insert_viajes, editFL_admin_insert_viajes;


    Button btnAgregrar_admin_insert_viajes;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viajes_crear);

        editId_admin_isert_viajes= findViewById(R.id.editId_admin_insert_viajes);
        //editFS_admin_insert_viajes = findViewById(R.id.editFS_admin_insert_viajes);
        //editFL_admin_insert_viajes= findViewById(R.id.editFL_admin_insert_viajes);
        editDestino_admin_isert_viajes = findViewById(R.id.editDestino_admin_insert_viajes);
        editId_barco_admin_insert_viajes= findViewById(R.id.editId_barco_admin_insert_viajes);
        edit_Id_patron_admin_insert_viajes = findViewById(R.id.edit_Id_patron_admin_insert_viajes);

        btnAgregrar_admin_insert_viajes=findViewById(R.id.btnAgregrar_admin_insert_viajes);
        btnAgregrar_admin_insert_viajes.setOnClickListener(this::onClick);
    }

    private void insertarViajeAdmin(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Viaje creado correctamente ", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(getApplicationContext(), admin_viajes_crud.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id_viaje", editId_admin_isert_viajes.getText().toString());
                parametros.put("fecha_salida", editFS_admin_insert_viajes.toString());
                parametros.put("fecha_llegada", editFL_admin_insert_viajes.toString());
                parametros.put("destino", editDestino_admin_isert_viajes.getText().toString());
                parametros.put("id_barco", editId_barco_admin_insert_viajes.getText().toString());
                parametros.put("id_patron", edit_Id_patron_admin_insert_viajes.getText().toString());

                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnAgregrar_admin_insert_viajes){
            insertarViajeAdmin("http://192.168.0.12/crud_club_barcos/admin/viajes/insert.php");
        }

    }
}