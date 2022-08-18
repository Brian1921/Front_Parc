package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class admin_barcos_agregar extends AppCompatActivity  implements View.OnClickListener{
    EditText editIdbarco, editbarco, editIdSociobarco, editIdAmabarco;
    Button btnAgregar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_barcos_agregar);
        editIdbarco = findViewById(R.id.editId_admin_crear_barco);
        editbarco = findViewById(R.id.editNom_admin_crear_barco);
        editIdSociobarco = findViewById(R.id.editIdSocio_admin_crear_barco);
        editIdAmabarco = findViewById(R.id.editIdAma_admin_crear_barco);

        btnAgregar = findViewById(R.id.btnAgregar_admin_barco);
        btnAgregar.setOnClickListener(this::onClick);
    }


    private void insertarBarcoAdmin(String url) {
        if(editIdbarco.getText().toString().isEmpty()){
            editIdbarco.setError("Digite el id");
        }else if(editbarco.getText().toString().isEmpty()){
            editbarco.setError("Digite el nombre");
        }else if(editIdSociobarco.getText().toString().isEmpty()){
            editIdSociobarco.setError("Digite el id del socio");
        }else if(editIdAmabarco.getText().toString().isEmpty()) {
            editIdAmabarco.setError("Digite el id del amarre");
        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Barco creado correctamente ", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), admin_barcos_crud.class);
                    startActivity(intent);
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
                    parametros.put("id", editIdbarco.getText().toString());
                    parametros.put("nom", editbarco.getText().toString());
                    parametros.put("id_so", editIdSociobarco.getText().toString());
                    parametros.put("id_ama", editIdAmabarco.getText().toString());
                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            }
        }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnAgregar_admin_barco){
            insertarBarcoAdmin("http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/barcos/insert.php");
        }
    }
}