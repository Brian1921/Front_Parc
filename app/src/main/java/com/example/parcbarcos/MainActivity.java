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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String [] cat = {"ADMINISTRADOR","SOCIO","PATRON"};

    AutoCompleteTextView autoCompleteTxt;

    ArrayAdapter<String> adapterItems;

    EditText editTextTextPersonName,editTextTextPassword;
    Button btnIngresar;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName=findViewById(R.id.txtUsuario);
        editTextTextPassword=findViewById(R.id.txtPass);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this::onClick);


        autoCompleteTxt =findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this,R.layout.item_list,cat);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                /*Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();*/
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
                        Intent intent = new Intent(getApplicationContext(), admin_menu.class);
                        startActivity(intent);

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
                    parametros.put("rol", "ADMINISTRADOR");
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
            //insertar("http://10.28.0.102:1021/comp2022/insert.php");
            validarUsuario("http://192.168.0.12/crud_club_barcos/inicio_sesion/validar.php");
        }
    }
}