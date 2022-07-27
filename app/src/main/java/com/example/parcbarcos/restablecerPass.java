package com.example.parcbarcos;

import androidx.annotation.Nullable;
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

public class restablecerPass extends AppCompatActivity {
    EditText editId, editPass, editRes;
    Button btnRecuperar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_pass);

        editId = findViewById(R.id.editId_recuperar);
        editPass = findViewById(R.id.editPass_recuperar);
        editRes = findViewById(R.id.editRes_recuperar);

        btnRecuperar=findViewById(R.id.btnRecuperarPass);
        btnRecuperar.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnRecuperarPass){
            recuperarPassword(view);
        }
    }
    public void recuperarPassword(View view){
        String urlupdate="http://192.168.0.12/crud_club_barcos/inicio_sesion/recuperar.php";
        //String urlupdate="http://192.168.1.1/crud_club_barcos/inicio_sesion/recuperar.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Contraseña recuperada correctamente", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error, verifique los datos", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id", editId.getText().toString());
                parametros.put("pass", editPass.getText().toString());
                parametros.put("res", editRes.getText().toString());
                return parametros;

            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}