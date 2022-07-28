package com.example.parcbarcos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class admin_patrones_editar extends AppCompatActivity {

    EditText editNom_admin_update_patron, editApe_admin_update_patron, editTel_admin_update_patron, editEm_admin_update_patron;
    Button btnEditar_admin_patron;
    private int position;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_patrones_editar);

        editNom_admin_update_patron = findViewById(R.id.editNom_admin_update_patron);
        editApe_admin_update_patron = findViewById(R.id.editApe_admin_update_patron);
        editTel_admin_update_patron = findViewById(R.id.editTel_admin_update_patron);
        editEm_admin_update_patron = findViewById(R.id.editEm_admin_update_patron);

        btnEditar_admin_patron=findViewById(R.id.btnEditar_admin_patron);
        btnEditar_admin_patron.setOnClickListener(this::onClick);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        editNom_admin_update_patron.setText(admin_patrones_crud.class_admin_patronesArrayList.get(position).getNom_patron());
        editApe_admin_update_patron.setText(admin_patrones_crud.class_admin_patronesArrayList.get(position).getApe_patron());
        editTel_admin_update_patron.setText(admin_patrones_crud.class_admin_patronesArrayList.get(position).getTel_patron());
        editEm_admin_update_patron.setText(admin_patrones_crud.class_admin_patronesArrayList.get(position).getEm_patron());
    }
    private void onClick(View view) {
        int id= view.getId();
        if(id==R.id.btnEditar_admin_patron){
            updateAdminPatrones(view);
        }
    }

    public void updateAdminPatrones(View view){
        if(editNom_admin_update_patron.getText().toString().isEmpty()){
            editNom_admin_update_patron.setError("Digite el id");
        }else if(editApe_admin_update_patron.getText().toString().isEmpty()){
            editApe_admin_update_patron.setError("Digite el id");
        }else if(editTel_admin_update_patron.getText().toString().isEmpty()) {
            editTel_admin_update_patron.setError("Digite el id");
        }else if(editEm_admin_update_patron.getText().toString().isEmpty()) {
            editEm_admin_update_patron.setError("Digite el id");
        }else{
                //String urlupdate="http://192.168.1.1/crud_club_barcos/admin/socios/update.php";
                String urlupdate="http://192.168.103.70/crud_club_barcos/admin/patrones/update.php";
                final  String nom=editNom_admin_update_patron.getText().toString().trim();
                final  String ape=editApe_admin_update_patron.getText().toString().trim();
                final  String tel=editTel_admin_update_patron.getText().toString().trim();
                final  String em=editEm_admin_update_patron.getText().toString().trim();

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Actualizando");
                progressDialog.dismiss();

                StringRequest request = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(admin_patrones_editar.this,response , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), admin_patrones_crud.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(admin_patrones_editar.this, error.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parametros = new HashMap<String,String>();
                        parametros.put("id", admin_patrones_crud.class_admin_patronesArrayList.get(position).getId_patron());
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
}