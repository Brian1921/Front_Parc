package com.example.parcbarcos;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class socio_viajes_insertar extends AppCompatActivity {
    EditText editId_socio_isert_viajes, editFS_socio_insert_viajes, editFL_socio_insert_viajes,editDestino_socio_isert_viajes,editId_barco_socio_insert_viajes,
            edit_Id_patron_socio_insert_viajes;

    /*DatePicker editFS_admin_insert_viajes;, editFL_admin_insert_viajes;*/
    DatePickerDialog.OnDateSetListener onDateSetListener, onDateSetListener1;

    Button btnAgregrar_socio_insert_viajes;
    RequestQueue requestQueue;

    final Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viajes_crear);



        editId_socio_isert_viajes= findViewById(R.id.editId_socio_insert_viajes);
        editFS_socio_insert_viajes = findViewById(R.id.editFS_socio_insert_viajes);
        editFL_socio_insert_viajes= findViewById(R.id.editFL_socio_insert_viajes);
        editDestino_socio_isert_viajes = findViewById(R.id.editDestino_socio_insert_viajes);
        editId_barco_socio_insert_viajes= findViewById(R.id.editId_barco_socio_insert_viajes);
        edit_Id_patron_socio_insert_viajes = findViewById(R.id.edit_Id_patron_socio_insert_viajes);

        btnAgregrar_socio_insert_viajes=findViewById(R.id.btnAgregrar_admin_insert_viajes);
        btnAgregrar_socio_insert_viajes.setOnClickListener(this::onClick);
        editFS_socio_insert_viajes.setOnClickListener(this::onClick);
        editFL_socio_insert_viajes.setOnClickListener(this::onClick);



    }

    private void insertarViajeSocio(String url) {
        if(editId_socio_isert_viajes.getText().toString().isEmpty()){
            editId_socio_isert_viajes.setError("Digite el id");

        }else if(editDestino_socio_isert_viajes.getText().toString().isEmpty()){
            editDestino_socio_isert_viajes.setError("Digite el destino");

        }else if(editId_barco_socio_insert_viajes.getText().toString().isEmpty()){
            editId_barco_socio_insert_viajes.setError("Digite el id del barco");

        }else if(edit_Id_patron_socio_insert_viajes.getText().toString().isEmpty()){
            edit_Id_patron_socio_insert_viajes.setError("Digite el id del patron");

        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Viaje creado correctamente ", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), socios_viajes_crud.class);
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
                    parametros.put("id_viaje", editId_socio_isert_viajes.getText().toString());
                    parametros.put("fecha_salida", editFS_socio_insert_viajes.toString());
                    parametros.put("fecha_llegada", editFL_socio_insert_viajes.toString());
                    parametros.put("destino", editDestino_socio_isert_viajes.getText().toString());
                    parametros.put("id_barco", editId_barco_socio_insert_viajes.getText().toString());
                    parametros.put("id_patron", edit_Id_patron_socio_insert_viajes.getText().toString());

                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }


    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnAgregrar_socio_insert_viajes){
            insertarViajeSocio("http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/socio/viajes/insert.php");
        }

        if(id==R.id.editFS_socio_insert_viajes){
            DatePickerDialog datePickerDialog = new DatePickerDialog(socio_viajes_insertar.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month+1;
                    String date = day+"/"+month+"/"+year;
                    editFS_socio_insert_viajes.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();

        } else if(id==R.id.editFL_socio_insert_viajes) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(socio_viajes_insertar.this , new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month+1;
                    String date = day+"/"+month+"/"+year;
                    editFL_socio_insert_viajes.setText(date);
                }
            },year, month, day);
            datePickerDialog.show();
        }
    }





}
