package com.example.parcbarcos;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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

public class socios_barcos_crud extends AppCompatActivity {
    class_socios_barcos sociosBarcos;
    public static ArrayList<class_socios_barcos> class_socios_barcosArrayList = new ArrayList<>();
    ListView listView;
    Adapter_class_socios_barcos adapter;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socios_barcos_crud);
        listView= findViewById(R.id.listReadSociosBarcos);
        adapter = new Adapter_class_socios_barcos(this, class_socios_barcosArrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogoItem = {"editar", "eliminar"};
                builder.setTitle(class_socios_barcosArrayList.get(position).getNom_barco() );

                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                //startActivity(new Intent(getApplicationContext(), admin_patrones_editar.class).putExtra("position", position));
                                break;
                            case 1:
                                //eliminarSocioBarcos(class_socios_barcosArrayList.get(position).getId_barco());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        listarBarcosSocios();
    }
    private void listarBarcosSocios() {
        String url="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/socio/barcos/read.php";
        final ProgressDialog progressDialog = new ProgressDialog(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                class_socios_barcosArrayList.clear();

                try {
                    JSONObject objetojson = new JSONObject(response);
                    JSONArray arrayjson = objetojson.getJSONArray("Datos");
                    for (int i = 0; i < arrayjson.length(); i++) {
                        //Toast.makeText(socios_barcos_crud.this, "Entro a for", Toast.LENGTH_SHORT).show();
                        JSONObject object = arrayjson.getJSONObject(i);

                        String id_bar = object.getString("id_barco");
                        String nom_bar = object.getString("nom_barco");
                        String nom_socio = object.getString("id_socio");
                        String id_amarre = object.getString("id_amarre");
                        sociosBarcos=new class_socios_barcos(id_bar, nom_bar, nom_socio, id_amarre);
                        class_socios_barcosArrayList.add(sociosBarcos);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(socios_barcos_crud.this, error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id","1234");
                return parametros;
            }

        };requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void agregarSocioBarco(View view){
        startActivity(new Intent(getApplicationContext(), socios_barcos_insertar.class));
    }

    public void eliminarSocioBarcos(String id){
        String urlel="http://"+getResources().getText(R.string.ip)+"/crud_club_barcos/socio/barcos/delete.php";
        StringRequest request = new StringRequest(Request.Method.POST, urlel, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(socios_barcos_crud.this, "Eliminando Barco", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(), socios_barcos_crud.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(socios_barcos_crud.this, "Error al eliminar patron", Toast.LENGTH_SHORT).show();
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