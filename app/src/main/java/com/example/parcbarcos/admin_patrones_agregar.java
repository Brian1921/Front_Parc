package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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

public class admin_patrones_agregar extends AppCompatActivity {

    EditText txtId_crear_patron, txtNom_crear_patron, txtApe_crear_patron, txtTel_crear_patron, txtEm_crear_patron, txtRes_crear_patron,
            txtUs_crear_patron, txtPas_crear_patron;

    RequestQueue requestQueue;
    Button btnCrear_patron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_patrones_agregar);
        txtId_crear_patron=findViewById(R.id.);
        txtNom_crear_patron=findViewById(R.id);
        txtApe_crear_patron=findViewById(R.id);
        txtTel_crear_patron=findViewById(R.id);
        txtEm_crear_patron=findViewById(R.id);
        txtRes_crear_patron=findViewById(R.id);
        txtUs_crear_patron=findViewById(R.id);
        txtPas_crear_patron=findViewById(R.id);

        btnCrear_patron=findViewById(R.id);
        btnCrear_patron.setOnClickListener(this::onClick());
    }

    private void insertarSocioAdmin(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Patron creado correctamente ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), admin_patrones_crud.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Ocurrio un error al registrar el patron", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id", txtId_crear_patron.getText().toString());
                parametros.put("nom", txtNom_crear_patron.getText().toString());
                parametros.put("ape", txtApe_crear_patron.getText().toString());
                parametros.put("tel", txtTel_crear_patron.getText().toString());
                parametros.put("em", txtEm_crear_patron.getText().toString());
                parametros.put("res", txtRes_crear_patron.getText().toString());
                parametros.put("us", txtUs_crear_patron.getText().toString());
                parametros.put("pas", txtPas_crear_patron.getText().toString());
                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}