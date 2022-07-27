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

public class Adapter_class_admin_patrones extends ArrayAdapter<class_admin_patrones> {
    Context context;
    List<class_admin_patrones> arrayPatrones;

    public Adapter_class_admin_patrones(@NonNull Context context, List<class_admin_patrones>arrayPatrones) {
        super(context, R.layout.list_patrones,arrayPatrones);
        this.context=context;
        this.arrayPatrones=arrayPatrones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_patrones, null,true);

        TextView id_patron= view.findViewById(R.id.txtId_patron);
        TextView nom_patron = view.findViewById(R.id.txtNom_patron);
        TextView ape_patron = view.findViewById(R.id.txtApe_patron);
        TextView tel_patron = view.findViewById(R.id.txtTel_patron);
        TextView em_patron = view.findViewById(R.id.txtEm_patron);
        TextView id_estado_patron = view.findViewById(R.id.txtId_estado_patron);

        id_patron.setText(arrayPatrones.get(position).getId_patron());
        nom_patron.setText(arrayPatrones.get(position).getNom_patron());
        ape_patron.setText(arrayPatrones.get(position).getApe_patron());
        tel_patron.setText(arrayPatrones.get(position).getTel_patron());
        em_patron.setText(arrayPatrones.get(position).getEm_patron());
        id_estado_patron.setText(arrayPatrones.get(position).getId_estado_patron());

        return view;
    }
}
