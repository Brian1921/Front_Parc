package com.example.parcbarcos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class patron_menu extends AppCompatActivity {

    class_patron_viajes viajes;
    public static ArrayList<class_patron_viajes> class_patron_viajesArrayList = new ArrayList<>();

    Button btnPatronSalir;

    ListView listView;
    Adapter_class_patron_viajes adapter;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patron_menu);

        btnPatronSalir= findViewById(R.id.btnPatronSalir);
        btnPatronSalir.setOnClickListener(this::onClick);

        listView = findViewById(R.id.listReadPatronViajes);
        adapter = new Adapter_class_patron_viajes(this, class_patron_viajesArrayList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?>  parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                builder.setTitle(class_patron_viajesArrayList.get(position).getId_viaje());
                CharSequence[] dialogoItem = {};
                builder.create().show();
            }
        });
        listarViajesPatron();
    }

    private void onClick(View view) {
        int id= view.getId();
        if(R.id.btnPatronSalir==id){
            Intent intent = new Intent(patron_menu.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    }

    private void listarViajesPatron() {
        String url = "http://" + getResources().getText(R.string.ip) + "/crud_club_barcos/patron/viajes/read.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                class_patron_viajesArrayList.clear();

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

                        viajes = new class_patron_viajes(id_vi,  fechSal_vi, fechLleg_vi, dest_vi, idBar_vi, idSoc_vi);
                        class_patron_viajesArrayList.add(viajes);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(patron_menu.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id", "9999");

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}