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

public class admin_barcos_crud extends AppCompatActivity {
    class_admin_barcos barcos;
    public static ArrayList<class_admin_barcos> class_admin_barcosArrayList = new ArrayList<>();
    ListView listView;
    Adapter_class_admin_barcos adapter;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_barcos_crud);
        listView= findViewById(R.id.listReadAdminBarcos);
        adapter = new Adapter_class_admin_barcos(this, class_admin_barcosArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogoItem = {"editar", "eliminar"};
                builder.setTitle(class_admin_barcosArrayList.get(position).getNom_barco() + " " + class_admin_barcosArrayList.get(position).getApe_socio());

                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                startActivity(new Intent(getApplicationContext(), admin_barcos_editar.class).putExtra("position", position));
                                break;
                            case 1:
                                //eliminarAdminPatrones(class_admin_barcosArrayList.get(position).getId_barco());
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
        String url="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/barcos/read.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                class_admin_barcosArrayList.clear();

                try {
                    //Toast.makeText(admin_socios_crud.this, response, Toast.LENGTH_SHORT).show();
                    JSONObject objetojson = new JSONObject(response);

                    JSONArray arrayjson = objetojson.getJSONArray("Datos");

                    for (int i = 0; i < arrayjson.length(); i++) {
                        //Toast.makeText(admin_socios_crud.this, "Entro a for", Toast.LENGTH_SHORT).show();
                        JSONObject object = arrayjson.getJSONObject(i);

                        String id_bar = object.getString("id_barco");
                        String nom_bar = object.getString("nom_barco");
                        String id_amarre = object.getString("id_amarre");
                        String nom_socio = object.getString("nom_socio");
                        String ape_socio = object.getString("ape_socio");
                        String id_estado_barco = object.getString("id_estado_barco");

                        String id_estado_pa="";
                        if(object.getString("id_estado_barco").equals("1")){
                            id_estado_barco = "Estado: Activo";
                        }else{
                            id_estado_barco = "Estado: Inactivo";
                        }

                        barcos = new class_admin_barcos(id_bar, nom_bar, id_amarre, nom_socio, ape_socio, id_estado_barco);
                        class_admin_barcosArrayList.add(barcos);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_barcos_crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void agregarAdminPatrones(View view){
        startActivity(new Intent(getApplicationContext(), admin_patrones_agregar.class));
    }

    public void eliminarAdminPatrones(String id){
        String urlel="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/admin/patrones/delete.php";
        StringRequest request = new StringRequest(Request.Method.POST, urlel, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(admin_barcos_crud.this, "Eliminando Patron", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(), admin_barcos_crud.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_barcos_crud.this, "Error al eliminar patron", Toast.LENGTH_SHORT).show();
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