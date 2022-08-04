package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registrarUsuarios extends AppCompatActivity {
    Spinner spinUsuarios;
    String rol;

    EditText txtId_crear_usuario, txtNom_crear_usuario, txtApe_crear_usuario, txtTel_crear_usuario, txtEm_crear_usuario, txtRes_crear_usuario, txtUs_crear_usuario, txtPas_crear_socio;
    RequestQueue requestQueue;
    Button btnCrear_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);

        txtId_crear_usuario=findViewById(R.id.edtIdUsu);
        txtNom_crear_usuario=findViewById(R.id.edtNomUsuo);
        txtApe_crear_usuario=findViewById(R.id.edtApeUsu);
        txtTel_crear_usuario=findViewById(R.id.edtTelUsu);
        txtEm_crear_usuario=findViewById(R.id.edtEmUsu);
        txtRes_crear_usuario=findViewById(R.id.edtResUsu);
        txtUs_crear_usuario=findViewById(R.id.edtNickUsu);
        txtPas_crear_socio=findViewById(R.id.edtPassUsu);
        ArrayList<String> roles = new ArrayList<String>();
        btnCrear_usuario=findViewById(R.id.btnAgregarUsu);

        btnCrear_usuario.setOnClickListener(this::onClick);
        txtTel_crear_usuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String x = editable.toString();
                if(txtTel_crear_usuario.getText().toString().length()<7){
                    txtTel_crear_usuario.setError("Lonqitud insuficiente");
                }
            }
        });
    }

    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnAgregarUsu){
            insertarUsu("http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/patrones/insert.php");
        }

    }


    private void insertarUsu(String url){

        if(txtId_crear_usuario.getText().toString().isEmpty()){
            txtId_crear_usuario.setError("Digite el id");
        }else if(txtNom_crear_usuario.getText().toString().isEmpty()){
            txtNom_crear_usuario.setError("Digite el nombre");
        }else if(txtApe_crear_usuario.getText().toString().isEmpty()){
            txtApe_crear_usuario.setError("Digite el apellido");
        }else if(txtTel_crear_usuario.getText().toString().isEmpty()){
            txtTel_crear_usuario.setError("Digite el telefono");
        }else if(Integer.parseInt(txtTel_crear_usuario.getText().toString())<=0 ){
            txtTel_crear_usuario.setError("Numero Invalido");
        }else if(txtEm_crear_usuario.getText().toString().isEmpty()){
            txtEm_crear_usuario.setError("Digite el email");
        }else if(ValidarEmail(txtEm_crear_usuario.getText().toString())==false){
            txtEm_crear_usuario.setError("Email invalido");
        }else if(txtRes_crear_usuario.getText().toString().isEmpty()){
            txtRes_crear_usuario.setError("Digite la respuesta de seguridad");
        }else if(txtUs_crear_usuario.getText().toString().isEmpty()){
            txtUs_crear_usuario.setError("Digite el usuario");
        }else if(txtPas_crear_socio.getText().toString().isEmpty()){
            txtPas_crear_socio.setError("Digite la contraseña");
        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Usuario creado correctamente ", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), admin_socios_crud.class);
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
                    parametros.put("id", txtId_crear_usuario.getText().toString());
                    parametros.put("nom", txtNom_crear_usuario.getText().toString());
                    parametros.put("ape", txtApe_crear_usuario.getText().toString());
                    parametros.put("tel", txtTel_crear_usuario.getText().toString());
                    parametros.put("em", txtEm_crear_usuario.getText().toString());
                    parametros.put("res", txtRes_crear_usuario.getText().toString());
                    parametros.put("us", txtUs_crear_usuario.getText().toString());
                    parametros.put("pas", txtPas_crear_socio.getText().toString());
                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    public boolean ValidarEmail (String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return  mather.find();
    }


}