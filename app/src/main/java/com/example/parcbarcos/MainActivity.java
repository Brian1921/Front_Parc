package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Spinner spnUsuarios;
    String rol;
    EditText editTextTextPersonName,editTextTextPassword;
    Button btnIngresar, btnInicio_recuperar;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName=findViewById(R.id.txtUsuario);
        editTextTextPassword=findViewById(R.id.txtPass);

        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this::onClick);

        btnInicio_recuperar = findViewById(R.id.btnInicio_recuperar);
        btnInicio_recuperar.setOnClickListener(this::onClick);

        spnUsuarios = (Spinner) findViewById(R.id.spnUsuarios);

        ArrayList<String> roles = new ArrayList<String>();

        roles.add("ADMINISTRADOR");
        roles.add("SOCIO");
        roles.add("PATRÓN");
        ArrayAdapter adp = new ArrayAdapter(MainActivity.this, R.layout.spinner_per,roles);
        spnUsuarios.setAdapter(adp);

        spnUsuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rol = (String) spnUsuarios.getAdapter().getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        
    }

    private void validarUsuario(String url){
        //validar no dejar espacios en blanco
        if(editTextTextPersonName.getText().toString().isEmpty()){
            editTextTextPersonName.setError("Digite el usuario");
        }
        else if(editTextTextPassword.getText().toString().isEmpty()){
            editTextTextPassword.setError("Digite el password");
        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if(!response.isEmpty()){

                        if(rol.equals("ADMINISTRADOR")){
                            Intent intent = new Intent(getApplicationContext(), admin_menu.class);
                            startActivity(intent);
                        }else if(rol.equals("SOCIO")){
                            Intent intent = new Intent(getApplicationContext(), socio_menu.class);
                            startActivity(intent);
                        }else if(rol.equals("PATRÓN")){
                            Intent intent = new Intent(getApplicationContext(), patron_menu.class);
                            startActivity(intent);
                        }


                    }else{
                        Toast.makeText(getApplicationContext(), "Ingreso de sesión inválido ", Toast.LENGTH_SHORT).show();
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
                    parametros.put("user", editTextTextPersonName.getText().toString());
                    parametros.put("pass", editTextTextPassword.getText().toString());
                    parametros.put("rol", rol);
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
        if(id==R.id.btnIngresar){
            //validarUsuario("http://192.168.1.1/crud_club_barcos/inicio_sesion/validar.php");
            validarUsuario("http://192.168.103.70/crud_club_barcos/inicio_sesion/validar.php");
        }
        if(id==R.id.btnInicio_recuperar){
            Intent intent = new Intent(getApplicationContext(), restablecerPass.class);
            startActivity(intent);
        }
    }
}