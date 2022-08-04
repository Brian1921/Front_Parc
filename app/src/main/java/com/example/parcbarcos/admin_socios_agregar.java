package com.example.parcbarcos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class admin_socios_agregar extends AppCompatActivity implements View.OnClickListener{

    EditText txtId_crear_socio, txtNom_crear_socio, txtApe_crear_socio, txtTel_crear_socio, txtEm_crear_socio, txtRes_crear_socio,
    txtUs_crear_socio, txtPas_crear_socio;
    RequestQueue requestQueue;
    Button btnCrear_socio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_socios_agregar);
        txtId_crear_socio=findViewById(R.id.editId_admin_crear_socio);
        txtNom_crear_socio=findViewById(R.id.editNom_admin_crear_socio);
        txtApe_crear_socio=findViewById(R.id.editApe_admin_crear_socio);
        txtTel_crear_socio=findViewById(R.id.editTel_admin_crear_socio);
        txtEm_crear_socio=findViewById(R.id.editEm_admin_crear_socio);
        txtRes_crear_socio=findViewById(R.id.editRes_admin_crear_socio);
        txtUs_crear_socio=findViewById(R.id.editUs_admin_crear_socio);
        txtPas_crear_socio=findViewById(R.id.editPass_admin_crear_socio);

        btnCrear_socio=findViewById(R.id.btnAgregar_admin_socio);
        btnCrear_socio.setOnClickListener(this::onClick);

        txtTel_crear_socio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String x = editable.toString();
                if(txtTel_crear_socio.getText().toString().length()<7){
                    txtTel_crear_socio.setError("Lonqitud insuficiente");
                }
            }

        });

    }



    private void insertarSocioAdmin(String url){

        if(txtId_crear_socio.getText().toString().isEmpty()){
            txtId_crear_socio.setError("Digite el id");
        }else if(txtNom_crear_socio.getText().toString().isEmpty()){
            txtNom_crear_socio.setError("Digite el nombre");
        }else if(txtApe_crear_socio.getText().toString().isEmpty()){
            txtApe_crear_socio.setError("Digite el apellido");
        }else if(txtTel_crear_socio.getText().toString().isEmpty()){
            txtTel_crear_socio.setError("Digite el telefono");
        }else if(Integer.parseInt(txtTel_crear_socio.getText().toString())<=0 ){
            txtTel_crear_socio.setError("Numero Invalido");
        }else if(txtEm_crear_socio.getText().toString().isEmpty()){
            txtEm_crear_socio.setError("Digite el email");
        }else if(ValidarEmail(txtEm_crear_socio.getText().toString())==false){
            txtEm_crear_socio.setError("Email invalido");
        }else if(txtRes_crear_socio.getText().toString().isEmpty()){
            txtRes_crear_socio.setError("Digite la respuesta de seguridad");
        }else if(txtUs_crear_socio.getText().toString().isEmpty()){
            txtUs_crear_socio.setError("Digite el usuario");
        }else if(txtPas_crear_socio.getText().toString().isEmpty()){
            txtPas_crear_socio.setError("Digite la contraseña");
        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Socio creado correctamente ", Toast.LENGTH_SHORT).show();
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
                    parametros.put("id", txtId_crear_socio.getText().toString());
                    parametros.put("nom", txtNom_crear_socio.getText().toString());
                    parametros.put("ape", txtApe_crear_socio.getText().toString());
                    parametros.put("tel", txtTel_crear_socio.getText().toString());
                    parametros.put("em", txtEm_crear_socio.getText().toString());
                    parametros.put("res", txtRes_crear_socio.getText().toString());
                    parametros.put("us", txtUs_crear_socio.getText().toString());
                    parametros.put("pas", txtPas_crear_socio.getText().toString());
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
        if(id==R.id.btnAgregar_admin_socio){
            insertarSocioAdmin("http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/socios/insert.php");
            ///insertarSocioAdmin("http://192.168.103.70/crud_club_barcos/admin/socios/insert.php");
        }

    }
    public boolean ValidarEmail (String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return  mather.find();
    }
}