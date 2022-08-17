package com.example.parcbarcos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

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


public class admin_usuarios_editar extends AppCompatActivity {

    EditText editUsuario;
    Button btnEditar_admin_usuario;
    private int position;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuarios_editar);
        Toast.makeText(admin_usuarios_editar.this, admin_usuarios_crud.class_admin_usuariosArrayList.get(position).getUsuario() , Toast.LENGTH_SHORT).show();

        editUsuario=findViewById(R.id.editUsuario_admin_update_usuario);

        btnEditar_admin_usuario=findViewById(R.id.btnEditar_admin_usuario);
        btnEditar_admin_usuario.setOnClickListener(this::Onclick);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        editUsuario.setText(admin_usuarios_crud.class_admin_usuariosArrayList.get(position).getUsuario());

    }


    private void Onclick(View view) {
        int id= view.getId();
        if(id==R.id.btnEditar_admin_usuario){
            updateAdminUsuarios(view);
        }
    }

    private void updateAdminUsuarios(View view) {
        if(editUsuario.getText().toString().isEmpty()){
            editUsuario.setError("Digite el nombre");
        }else{
            String urlupdate="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/usuarios/update.php";
            final  String usuario=editUsuario.getText().toString().trim();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Actualizando");
            progressDialog.dismiss();

            StringRequest request = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(admin_usuarios_editar.this ,response , Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), admin_usuarios_crud.class));
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(admin_usuarios_editar.this, error.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros = new HashMap<String,String>();
                    parametros.put("id", admin_usuarios_crud.class_admin_usuariosArrayList.get(position).getId_usuario());
                    parametros.put("usuario", usuario);

                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
}