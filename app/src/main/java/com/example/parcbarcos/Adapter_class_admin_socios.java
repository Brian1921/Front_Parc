package com.example.parcbarcos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter_class_admin_socios extends ArrayAdapter<class_admin_socios> {
    Context context;
    List<class_admin_socios> arraySocios;

    public Adapter_class_admin_socios(@NonNull Context context, List<class_admin_socios> arraySocios) {
        super(context, R.layout.list_socios, arraySocios);
        this.context=context;
        this.arraySocios=arraySocios;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_socios,  null, true);


        TextView txtId_socio = view.findViewById(R.id.txtId_socio);
        TextView txtNom_socio = view.findViewById(R.id.txtNom_socio);
        TextView txtApe_socio = view.findViewById(R.id.txtApe_socio);
        TextView txtTel_socio = view.findViewById(R.id.txtTel_socio);
        TextView txtEm_socio = view.findViewById(R.id.txtEm_socio);
        TextView txtId_estado_socio = view.findViewById(R.id.txtId_estado_socio);

        txtId_socio.setText(arraySocios.get(position).getId_socio());
        txtNom_socio.setText(arraySocios.get(position).getNom_socio());
        txtApe_socio.setText(arraySocios.get(position).getApe_socio());
        txtTel_socio.setText(arraySocios.get(position).getTel_socio());
        txtEm_socio.setText(arraySocios.get(position).getEm_socio());
        txtId_estado_socio.setText(arraySocios.get(position).getId_estado_socio());

        return view;
    }
}
