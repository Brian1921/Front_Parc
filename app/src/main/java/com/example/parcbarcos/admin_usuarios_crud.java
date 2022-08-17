package com.example.parcbarcos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class admin_usuarios_crud extends AppCompatActivity {
    class_admin_usuarios usuarios;
    public static ArrayList<class_admin_usuarios> class_admin_usuariosArrayList = new ArrayList<>();
    ListView listView;
    Adapter_class_admin_usuarios adapter;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_usuarios_crud);
        listView= findViewById(R.id.listReadAdminUsuarios);
        adapter = new Adapter_class_admin_usuarios(this, class_admin_usuariosArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogoItem = {"editar"};
                builder.setTitle(class_admin_usuariosArrayList.get(position).getUsuario());

                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(), admin_usuarios_editar.class).putExtra("position", position));
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        listarUsuarioAdmin();
    }

    private void listarUsuarioAdmin() {
        String url="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/usuarios/read.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                class_admin_usuariosArrayList.clear();

                try{
                    JSONObject objetojson = new JSONObject(response);
                    JSONArray arrayjson = objetojson.getJSONArray("Datos");

                    for (int i = 0; i < arrayjson.length(); i++) {
                        JSONObject object = arrayjson.getJSONObject(i);

                        String id_usuario = object.getString("id_u");
                        String usuario = object.getString("usuario");
                        String descrip_rol = object.getString("descrip_rol");

                        usuarios=new class_admin_usuarios(id_usuario,usuario,descrip_rol);
                        class_admin_usuariosArrayList.add(usuarios);
                        adapter.notifyDataSetChanged();
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_usuarios_crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}