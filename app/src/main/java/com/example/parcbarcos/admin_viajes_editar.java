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

public class admin_viajes_editar extends AppCompatActivity {
    EditText edtFeSal_admin_update_viaje, edtFeLle_admin_update_viaje, edtDestino_admin_update_viaje, edtIdBarco_admin_update_viaje, edtIdPatron_admin_update_viaje;
    Button btnEditar_admin_viaje;
    private int position;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viajes_editar);
        edtFeSal_admin_update_viaje = findViewById(R.id.edtFeSal_admin_update_viaje);
        edtFeLle_admin_update_viaje = findViewById(R.id.edtFeLle_admin_update_viaje);
        edtDestino_admin_update_viaje = findViewById(R.id.edtDestino_admin_update_viaje);
        edtIdBarco_admin_update_viaje = findViewById(R.id.edtIdBarco_admin_update_viaje);
        edtIdPatron_admin_update_viaje = findViewById(R.id.edtIdPatron_admin_update_viaje);

        btnEditar_admin_viaje=findViewById(R.id.btnEditar_admin_viaje);
        btnEditar_admin_viaje.setOnClickListener(this::onClick);


        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        edtFeSal_admin_update_viaje.setText(admin_viajes_crud.class_admin_viajesArrayList.get(position).getFecha_salida());
        edtFeLle_admin_update_viaje.setText(admin_viajes_crud.class_admin_viajesArrayList.get(position).getFecha_llegada());
        edtDestino_admin_update_viaje.setText(admin_viajes_crud.class_admin_viajesArrayList.get(position).getDestino());
        edtIdBarco_admin_update_viaje.setText(admin_viajes_crud.class_admin_viajesArrayList.get(position).getId_barco());
        edtIdPatron_admin_update_viaje.setText(admin_viajes_crud.class_admin_viajesArrayList.get(position).getId_patron());
    }

    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnEditar_admin_viaje){
            updateAdminViajes(view);
        }
    }

    public void updateAdminViajes(View view){
        if(edtFeSal_admin_update_viaje.getText().toString().isEmpty()){
            edtFeSal_admin_update_viaje.setError("Digite la fecha de salida");
        }else if(edtFeLle_admin_update_viaje.getText().toString().isEmpty()){
            edtFeLle_admin_update_viaje.setError("Digite la fecha de llegada");
        }else if(edtDestino_admin_update_viaje.getText().toString().isEmpty()){
            edtDestino_admin_update_viaje.setError("Digite el destino");
        }else if(edtIdBarco_admin_update_viaje.getText().toString().isEmpty()){
            edtIdBarco_admin_update_viaje.setError("Digite el ID barco");
        }else if(edtIdPatron_admin_update_viaje.getText().toString().isEmpty()){
            edtIdPatron_admin_update_viaje.setError("Digite el ID patrón");
        }else{
            String urlupdate="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/viajes/update.php";
            final  String fecha_salida=edtFeSal_admin_update_viaje.getText().toString().trim();
            final  String fecha_llegada=edtFeLle_admin_update_viaje.getText().toString().trim();
            final  String destino=edtDestino_admin_update_viaje.getText().toString().trim();
            final  String id_barco=edtIdBarco_admin_update_viaje.getText().toString().trim();
            final  String id_patron=edtIdPatron_admin_update_viaje.getText().toString().trim();

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Actualizando");
            progressDialog.dismiss();

            StringRequest request = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(admin_viajes_editar.this,response , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), admin_viajes_crud.class));
                    finish();
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(admin_viajes_editar.this, error.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros = new HashMap<String,String>();
                    parametros.put("id_viaje", admin_viajes_crud.class_admin_viajesArrayList.get(position).getId_viaje());
                    parametros.put("fecha_salida", fecha_salida);
                    parametros.put("fecha_llegada", fecha_salida);
                    parametros.put("destino", destino);
                    parametros.put("id_barco", id_barco);
                    parametros.put("id_patron", id_patron);
                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
}