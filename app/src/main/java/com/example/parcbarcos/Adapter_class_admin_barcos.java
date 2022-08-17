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

public class Adapter_class_admin_barcos extends ArrayAdapter<class_admin_barcos> {
    Context context;
    List<class_admin_barcos> arrayBarcos;

    public Adapter_class_admin_barcos(@NonNull Context context, List<class_admin_barcos>arrayBarcos){
        super(context, R.layout.list_barcos, arrayBarcos);
        this.context=context;
        this.arrayBarcos=arrayBarcos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barcos, null,true);

        TextView id_barco= view.findViewById(R.id.txtId_barco);
        TextView nom_barco = view.findViewById(R.id.txtNom_barco);
        TextView id_amarre = view.findViewById(R.id.txt_amarre);
        TextView nom_socio = view.findViewById(R.id.txtNom_socio);
        TextView ape_socio = view.findViewById(R.id.txtApe_socio);
        TextView id_estado_barco = view.findViewById(R.id.txtId_estado_barco);

        id_barco.setText(arrayBarcos.get(position).getId_barco());
        nom_barco.setText(arrayBarcos.get(position).getNom_barco());
        id_amarre.setText(arrayBarcos.get(position).getId_amarre());
        nom_socio.setText(arrayBarcos.get(position).getNom_socio());
        ape_socio.setText(arrayBarcos.get(position).getApe_socio());
        id_estado_barco.setText(arrayBarcos.get(position).getId_estado_barco());

        return view;
    }
}
