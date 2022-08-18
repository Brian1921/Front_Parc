package com.example.parcbarcos;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class socios_patrones_crud extends AppCompatActivity {
    Class_socios_patrones sociosPatrones;
    public static ArrayList<Class_socios_patrones> class_socios_patronesArrayList = new ArrayList<>();
    ListView listView;
    Adapter_class_socios_patrones adapter;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socios_patrones_crud);
        listView= findViewById(R.id.listReadSociosPatrones);
        adapter = new Adapter_class_socios_patrones(this, class_socios_patronesArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogoItem = {"editar", "eliminar"};
                builder.setTitle(class_socios_patronesArrayList.get(position).getNom_patron() + " " + class_socios_patronesArrayList.get(position).getApe_patron());

                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                //startActivity(new Intent(getApplicationContext(),admin_patrones_editar.class).putExtra("position", position));
                                break;
                            case 1:
                                //eliminarAdminPatrones(class_socios_patronesArrayList.get(position).getId_patron());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        listarPatronesAdmin();
    }
    private void listarPatronesAdmin() {
        String url="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/socio/patrones/read.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                class_socios_patronesArrayList.clear();

                try {
                    //Toast.makeText(admin_socios_crud.this, response, Toast.LENGTH_SHORT).show();
                    JSONObject objetojson = new JSONObject(response);

                    JSONArray arrayjson = objetojson.getJSONArray("Datos");

                    for (int i = 0; i < arrayjson.length(); i++) {
                        //Toast.makeText(admin_socios_crud.this, "Entro a for", Toast.LENGTH_SHORT).show();
                        JSONObject object = arrayjson.getJSONObject(i);

                        String id_pa = object.getString("id_patron");
                        String nom_pa = object.getString("nom_patron");
                        String ape_pa = object.getString("ape_patron");
                        String tel_pa = object.getString("tel_patron");
                        String em_pa = object.getString("em_patron");

                        String id_estado_pa="";
                        if(object.getString("id_estado_patron").equals("1")){
                            id_estado_pa = "Estado: Activo";
                        }else{
                            id_estado_pa = "Estado: Inactivo";
                        }

                        sociosPatrones = new Class_socios_patrones(id_pa, nom_pa, ape_pa, tel_pa, em_pa);
                        class_socios_patronesArrayList.add(sociosPatrones);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(socios_patrones_crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void agregarAdminPatrones(View view){
        startActivity(new Intent(getApplicationContext(),admin_patrones_agregar.class));
    }

    public void eliminarAdminPatrones(String id){
        String urlel="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/patrones/delete.php";
        StringRequest request = new StringRequest(Request.Method.POST, urlel, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(socios_patrones_crud.this, "Eliminando Patron", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(), socios_patrones_crud.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(socios_patrones_crud.this, "Error al eliminar patron", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id", id);

                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}