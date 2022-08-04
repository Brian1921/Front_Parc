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

public class admin_patrones_crud extends AppCompatActivity {
    class_admin_patrones patrones;
    public static ArrayList<class_admin_patrones> class_admin_patronesArrayList = new ArrayList<>();
    ListView listView;
    Adapter_class_admin_patrones adapter;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_patrones_crud);
        listView= findViewById(R.id.listReadAdminPatrones);
        adapter = new Adapter_class_admin_patrones(this, class_admin_patronesArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogoItem = {"editar", "eliminar"};
                builder.setTitle(class_admin_patronesArrayList.get(position).getNom_patron() + " " + class_admin_patronesArrayList.get(position).getApe_patron());

                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(),admin_patrones_editar.class).putExtra("position", position));
                                break;
                            case 1:
                                eliminarAdminPatrones(class_admin_patronesArrayList.get(position).getId_patron());
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
        String url="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/patrones/read.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                class_admin_patronesArrayList.clear();

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

                        patrones = new class_admin_patrones(id_pa, nom_pa, ape_pa, tel_pa, em_pa, id_estado_pa);
                        class_admin_patronesArrayList.add(patrones);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_patrones_crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(admin_patrones_crud.this, "Eliminando Patron", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(), admin_patrones_crud.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_patrones_crud.this, "Error al eliminar patron", Toast.LENGTH_SHORT).show();
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