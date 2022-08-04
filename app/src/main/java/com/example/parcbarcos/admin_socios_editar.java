package com.example.parcbarcos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class admin_socios_editar extends AppCompatActivity {

    EditText editNom_admin_update_socio, editApe_admin_update_socio, editTel_admin_update_socio, editEm_admin_update_socio;
    Button btnEditar_admin_socio;
    private int position;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_socios_editar);
        editNom_admin_update_socio = findViewById(R.id.editNom_admin_update_socio);
        editApe_admin_update_socio = findViewById(R.id.editApe_admin_update_socio);
        editTel_admin_update_socio = findViewById(R.id.editTel_admin_update_socio);
        editEm_admin_update_socio = findViewById(R.id.editEm_admin_update_socio);

        btnEditar_admin_socio=findViewById(R.id.btnEditar_admin_socio);
        btnEditar_admin_socio.setOnClickListener(this::onClick);

        editTel_admin_update_socio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String x = editable.toString();
                if(editTel_admin_update_socio.getText().toString().length()<7){
                    editTel_admin_update_socio.setError("Lonqitud insuficiente");
                }
            }

        });

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        editNom_admin_update_socio.setText(admin_socios_crud.class_admin_sociosArrayList.get(position).getNom_socio());
        editApe_admin_update_socio.setText(admin_socios_crud.class_admin_sociosArrayList.get(position).getApe_socio());
        editTel_admin_update_socio.setText(admin_socios_crud.class_admin_sociosArrayList.get(position).getTel_socio());
        editEm_admin_update_socio.setText(admin_socios_crud.class_admin_sociosArrayList.get(position).getEm_socio());
    }

    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnEditar_admin_socio){
            updateAdminSocios(view);
        }
    }

    public void updateAdminSocios(View view){
        if(editNom_admin_update_socio.getText().toString().isEmpty()){
            editNom_admin_update_socio.setError("Digite el nombre");
        }else if(editApe_admin_update_socio.getText().toString().isEmpty()){
            editApe_admin_update_socio.setError("Digite el apellido");
        }else if(editTel_admin_update_socio.getText().toString().isEmpty()){
            editTel_admin_update_socio.setError("Digite el telefono");
        }else if(Integer.parseInt(editTel_admin_update_socio.getText().toString())<=0 ){
            editTel_admin_update_socio.setError("Numero Invalido");
        }else if(editEm_admin_update_socio.getText().toString().isEmpty()){
            editEm_admin_update_socio.setError("Digite el email");
        }else if(ValidarEmail(editEm_admin_update_socio.getText().toString())==false){
            editEm_admin_update_socio.setError("Email invalido");
        }else{
            String urlupdate="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/socios/update.php";
            final  String nom=editNom_admin_update_socio.getText().toString().trim();
            final  String ape=editApe_admin_update_socio.getText().toString().trim();
            final  String tel=editTel_admin_update_socio.getText().toString().trim();
            final  String em=editEm_admin_update_socio.getText().toString().trim();

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Actualizando");
            progressDialog.dismiss();

            StringRequest request = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(admin_socios_editar.this,response , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), admin_socios_crud.class));
                    finish();
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(admin_socios_editar.this, error.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros = new HashMap<String,String>();
                    parametros.put("id", admin_socios_crud.class_admin_sociosArrayList.get(position).getId_socio());
                    parametros.put("nom", nom);
                    parametros.put("ape", ape);
                    parametros.put("tel", tel);
                    parametros.put("em", em);
                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
    public boolean ValidarEmail (String email){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return  mather.find();
    }
}