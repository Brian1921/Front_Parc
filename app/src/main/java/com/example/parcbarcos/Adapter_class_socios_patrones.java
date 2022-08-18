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

public class Adapter_class_socios_patrones extends ArrayAdapter<Class_socios_patrones> {
    Context context;
    List<Class_socios_patrones> arraySocioPatrones;

    public Adapter_class_socios_patrones(@NonNull Context context, List<Class_socios_patrones>arraySocioPatrones) {
        super(context, R.layout.list_socios_patrones,arraySocioPatrones);
        this.context=context;
        this.arraySocioPatrones=arraySocioPatrones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_socios_patrones, null,true);

        TextView id_patron= view.findViewById(R.id.txt_Socio_Id_patron);
        TextView nom_patron = view.findViewById(R.id.txt_Socio_Nom_patron);
        TextView ape_patron = view.findViewById(R.id.txt_Socio_Ape_patron);
        TextView tel_patron = view.findViewById(R.id.txt_Socio_telefono_patron);
        TextView em_patron = view.findViewById(R.id.txt_Socio_em_patron);

        id_patron.setText(arraySocioPatrones.get(position).getId_patron());
        nom_patron.setText(arraySocioPatrones.get(position).getNom_patron());
        ape_patron.setText(arraySocioPatrones.get(position).getApe_patron());
        tel_patron.setText(arraySocioPatrones.get(position).getTel_patron());
        em_patron.setText(arraySocioPatrones.get(position).getEm_patron());

        return view;
    }
}
