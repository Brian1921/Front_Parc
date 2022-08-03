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

public class admin_patrones_agregar extends AppCompatActivity implements View.OnClickListener{

    EditText txtId_crear_patron, txtNom_crear_patron, txtApe_crear_patron, txtTel_crear_patron, txtEm_crear_patron, txtRes_crear_patron,
            txtUs_crear_patron, txtPas_crear_patron;

    RequestQueue requestQueue;
    Button btnCrear_patron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_patrones_agregar);
        txtId_crear_patron=findViewById(R.id.editId_admin_crear_patron);
        txtNom_crear_patron=findViewById(R.id.editNom_admin_crear_patron);
        txtApe_crear_patron=findViewById(R.id.editApe_admin_crear_patron);
        txtTel_crear_patron=findViewById(R.id.editTel_admin_crear_patron);
        txtEm_crear_patron=findViewById(R.id.editEm_admin_crear_patron);
        txtRes_crear_patron=findViewById(R.id.editRes_admin_crear_patron);
        txtUs_crear_patron=findViewById(R.id.editUs_admin_crear_patron);
        txtPas_crear_patron=findViewById(R.id.editPass_admin_crear_patron);

        btnCrear_patron=findViewById(R.id.btnAgregar_admin_patron);
        btnCrear_patron.setOnClickListener(this::onClick);
    }

    private void insertarPatronAdmin(String url) {
        if(txtId_crear_patron.getText().toString().isEmpty()){
            txtId_crear_patron.setError("Digite el id");
        }else if(txtNom_crear_patron.getText().toString().isEmpty()){
            txtNom_crear_patron.setError("Digite el nombre");
        }else if(txtApe_crear_patron.getText().toString().isEmpty()){
            txtApe_crear_patron.setError("Digite el apellido");
        }else if(txtTel_crear_patron.getText().toString().isEmpty()){
            txtTel_crear_patron.setError("Digite el telefono");
        }else if(txtEm_crear_patron.getText().toString().isEmpty()){
            txtEm_crear_patron.setError("Digite el email");
        }else if(txtRes_crear_patron.getText().toString().isEmpty()){
            txtRes_crear_patron.setError("Digite la respuesta de seguridad");
        }else if(txtUs_crear_patron.getText().toString().isEmpty()){
            txtUs_crear_patron.setError("Digite el usuario");
        }else if(txtPas_crear_patron.getText().toString().isEmpty()){
            txtPas_crear_patron.setError("Digite la contraseña");
        }else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Patron creado correctamente ", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), admin_patrones_crud.class);
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

    @Override
    public void onClick(View view) {
        int id= view.getId();
        String url=getResources().getString(R.string.ip)+"crud_club_barcos/admin/patrones/insert.php";
        if(id==R.id.btnAgregar_admin_patron){
            insertarPatronAdmin(url);
        }

    }
}