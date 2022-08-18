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

public class Adapter_class_socios_barcos extends ArrayAdapter<class_socios_barcos> {
    Context context;
    List<class_socios_barcos> arraySocioBarcos;

    public Adapter_class_socios_barcos(@NonNull Context context, List<class_socios_barcos>arraySocioBarcos){
        super(context, R.layout.list_socios_barcos, arraySocioBarcos);
        this.context=context;
        this.arraySocioBarcos=arraySocioBarcos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_socios_barcos, null,true);

        TextView id_barco= view.findViewById(R.id.txt_Socio_Id_barco);
        TextView nom_barco = view.findViewById(R.id.txt_Socio_Nom_barco);
        TextView nom_socio = view.findViewById(R.id.txt_Socio_Nom_socio);
        TextView id_amarre = view.findViewById(R.id.txt_Socio_amarre);

        id_barco.setText(arraySocioBarcos.get(position).getId_barco());
        nom_barco.setText(arraySocioBarcos.get(position).getNom_barco());
        nom_socio.setText(arraySocioBarcos.get(position).getId_socio());
        id_amarre.setText(arraySocioBarcos.get(position).getNom_amarre());

        return view;
    }
}
