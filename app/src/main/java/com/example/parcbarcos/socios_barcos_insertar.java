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

import java.util.HashMap;
import java.util.Map;

public class socios_barcos_insertar extends AppCompatActivity {
    EditText editId_socio_isert_barco, editNom_barco_insert_barco, editId_amarre_insert_barco;
    DatePickerDialog.OnDateSetListener onDateSetListener, onDateSetListener1;

    Button btnAgregrar_socio_insert_barco;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socios_barcos_insertar);

        editId_socio_isert_barco= findViewById(R.id.edtSocioIdBarco);
        editNom_barco_insert_barco = findViewById(R.id.txtSocioNomBarco);
        editId_amarre_insert_barco =  findViewById(R.id.edtSocioIdAmarre);

        btnAgregrar_socio_insert_barco=findViewById(R.id.btnSocioAggBarco);
        btnAgregrar_socio_insert_barco.setOnClickListener(this::onClick);
        editNom_barco_insert_barco.setOnClickListener(this::onClick);
        editId_amarre_insert_barco.setOnClickListener(this::onClick);



    }

    private void insertarBarcoSocio(String url) {
        if(editId_socio_isert_barco.getText().toString().isEmpty()){
            editId_socio_isert_barco.setError("Digite el id");

        }else if(editNom_barco_insert_barco.getText().toString().isEmpty()){
            editNom_barco_insert_barco.setError("Digite el nombre del barco");

        }else if(editId_amarre_insert_barco.getText().toString().isEmpty()){
            editId_amarre_insert_barco.setError("Digite el id del amarre");


        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "barco creado correctamente ", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), socios_barcos_crud.class);
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
                    parametros.put("id", editId_socio_isert_barco.getText().toString());
                    parametros.put("nom", editNom_barco_insert_barco.toString());
                    parametros.put("id_ama", editId_amarre_insert_barco.toString());

                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }


    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnSocioAggBarco){
            insertarBarcoSocio("http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/socio/barcos/insert.php");
        }

        }



}
