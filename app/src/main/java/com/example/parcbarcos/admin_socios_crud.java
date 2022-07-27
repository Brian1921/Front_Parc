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

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class admin_socios_crud extends AppCompatActivity {

    ListView listView1;
    Adapter_class_admin_socios adapter1;

    public static ArrayList<class_admin_socios> class_admin_sociosArrayList = new ArrayList<>();
    String url="http://192.168.0.12/crud_club_barcos/admin/socios/read.php";

    class_admin_socios socios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_socios_crud);

        listView1= findViewById(R.id.listReadAdminSocios);
        adapter1 = new Adapter_class_admin_socios(this, class_admin_sociosArrayList);
        listView1.setAdapter(adapter1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem = {"editar", "eliminar"};
                builder.setTitle(class_admin_sociosArrayList.get(position).getNom_socio()+" "+class_admin_sociosArrayList.get(position).getApe_socio());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                //startActivity(new Intent(getApplicationContext(),admin_socios_crud_editar.class).putExtra("position", position));
                                break;
                            case 1:
                                //ELiminarAdminSocio(class_admin_sociosArrayList.get(position).getId_socio());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        listarSociosAdmin();
    }

    private void listarSociosAdmin() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                class_admin_sociosArrayList.clear();

                try {
                    JSONObject objetojson = new JSONObject(response);

                    JSONArray arrayjson = objetojson.getJSONArray("Datos");

                    for (int i = 0; i < arrayjson.length(); i++) {
                        //Toast.makeText(admin_socios_crud.this, "Entro a for", Toast.LENGTH_SHORT).show();
                        JSONObject object = arrayjson.getJSONObject(i);

                        String id_so = object.getString("id_socio");
                        String nom_so = object.getString("nom_socio");
                        String ape_so = object.getString("ape_socio");
                        String tel_so = object.getString("tel_socio");
                        String em_so = object.getString("em_socio");

                        String id_estado_so="";
                        if(object.getString("id_estado_socio").equals("1")){
                            id_estado_so = "Estado: Activo";
                        }else{
                            id_estado_so = "Estado: Inactivo";
                        }

                        socios = new class_admin_socios(id_so, nom_so, ape_so, tel_so, em_so, id_estado_so);
                        class_admin_sociosArrayList.add(socios);
                        adapter1.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_socios_crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void agregarAdminSocio(View view){
        startActivity(new Intent(getApplicationContext(),admin_socios_agregar.class));
    }
}