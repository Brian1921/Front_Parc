package com.example.parcbarcos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class socio_barcos_editar extends AppCompatActivity {
    EditText EditNom_barco_socio,EditId_amarre_socio;
    Button btnEditar_socio_barco;

    private int position;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socios_barcos_editar);

        EditNom_barco_socio = findViewById(R.id.EditNom_barco_admin);
        EditId_amarre_socio = findViewById(R.id.EditId_amarre);

        btnEditar_socio_barco = findViewById(R.id.btnEditar_admin_barco);
        btnEditar_socio_barco.setOnClickListener(this::Onclick);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        EditNom_barco_socio.setText(admin_barcos_crud.class_admin_barcosArrayList.get(position).getNom_barco());
        EditId_amarre_socio.setText(admin_barcos_crud.class_admin_barcosArrayList.get(position).getId_amarre());
    }
    private void Onclick(View view) {
        int id= view.getId();
        if(id==R.id.btnEditar_admin_barco){
            updateAdminBarcos(view);
        }
    }
    private void updateAdminBarcos(View view) {
        if(EditNom_barco_socio.getText().toString().isEmpty()){
            EditNom_barco_socio.setError("Digite el nombre");
        }else if(EditId_amarre_socio.getText().toString().isEmpty()){
            EditId_amarre_socio.setError("Digite el id de amarre");
        }else {
            String urlupdate = "http://" + getResources().getText(R.string.ip) + "/crud_club_barcos/socio/barcos/update.php";
            final String nom_barco = EditNom_barco_socio.getText().toString().trim();
            final String id_amarre = EditId_amarre_socio.getText().toString().trim();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Actualizando");
            progressDialog.dismiss();

            StringRequest request = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Toast.makeText(socio_barcos_editar.this, response, Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), socios_barcos_crud.class));
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(socio_barcos_editar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("id", admin_barcos_crud.class_admin_barcosArrayList.get(position).getId_barco());
                    parametros.put("nom", nom_barco);
                    parametros.put("id_ama", id_amarre);

                    return parametros;
                }
            };
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
}
