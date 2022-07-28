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

public class admin_viajes_crud extends AppCompatActivity {

    //String url="http://192.168.1.1/crud_club_barcos/admin/patrones/read.php";
    String url ="http://192.168.0.12/crud_club_barcos/admin/viajes/read.php";
    class_admin_viajes viajes;
    public static ArrayList<class_admin_viajes> class_admin_viajesArrayList = new ArrayList<>();
    ListView listView;
    Adapter_class_admin_viajes adapter;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viajes_crud);
        listView = findViewById(R.id.listReadAdminViajes);
        adapter = new Adapter_class_admin_viajes(this, class_admin_viajesArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogoItem = {"editar", "eliminar"};
                builder.setTitle(class_admin_viajesArrayList.get(position).getId_viaje() + " " + class_admin_viajesArrayList.get(position).getDestino());

                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                //startActivity(new Intent(getApplicationContext(),admin_patrones_editar.class).putExtra("position", position));
                                break;
                            case 1:
                                eliminarAdminViajes(class_admin_viajesArrayList.get(position).getId_viaje());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        listarViajesAdmin();
    }

    private void listarViajesAdmin(){
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                class_admin_viajesArrayList.clear();
                try{
                    JSONObject objetojson = new JSONObject(response);
                    JSONArray arrayjson = objetojson.getJSONArray("Datos");
                    for (int i = 0; i < arrayjson.length(); i++) {
                        JSONObject object = arrayjson.getJSONObject(i);
                        String id_vi = object.getString("id_viaje");
                        String fechSal_vi = object.getString("fecha_salida");
                        String fechLleg_vi = object.getString("fecha_llegada");
                        String dest_vi = object.getString("destino");
                        String idBar_vi = object.getString("nom_barco");
                        String idSoc_vi = object.getString("nom_socio");
                        String idPat_vi = object.getString("nom_patron");
                        String id_estado_vi="";
                        if(object.getString("id_estado_viaje").equals("1")){
                            id_estado_vi = "Estado: Activo";
                        }else{
                            id_estado_vi = "Estado: Eliminado";
                        }

                        viajes = new class_admin_viajes(id_vi, dest_vi, fechSal_vi, fechLleg_vi, idBar_vi, idPat_vi, idSoc_vi,id_estado_vi);
                        class_admin_viajesArrayList.add(viajes);
                        adapter.notifyDataSetChanged();
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_viajes_crud.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void agregarAdminViaje(View view){
        startActivity(new Intent(getApplicationContext(),admin_viajes_crear.class));
    }

    public void eliminarAdminViajes(String id){
        //String urlel="http://192.168.1.1/crud_club_barcos/admin/socios/delete.php";
        String urlel="http://192.168.0.12/crud_club_barcos/admin/viajes/delete.php";
        StringRequest request = new StringRequest(Request.Method.POST, urlel, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(admin_viajes_crud.this, "Eliminando viaje", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(), admin_viajes_crud.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(admin_viajes_crud.this, "Error al eliminar viaje", Toast.LENGTH_SHORT).show();
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
