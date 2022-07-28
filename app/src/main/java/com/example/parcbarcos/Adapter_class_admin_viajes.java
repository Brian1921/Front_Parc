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

public class Adapter_class_admin_viajes extends ArrayAdapter<class_admin_viajes> {
    Context context;
    List<class_admin_viajes> arrayViajes;


    public Adapter_class_admin_viajes(@NonNull Context context, List<class_admin_viajes> arrayViajes) {
        super(context, R.layout.list_viajes, arrayViajes);
        this.context=context;
        this.arrayViajes=arrayViajes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_viajes, null, true);

        TextView txtId_viaje = view.findViewById(R.id.txtId_viaje);
        TextView txtDestino = view.findViewById(R.id.txtDestino);
        TextView txtFecha_Sal = view.findViewById(R.id.txtFecha_Sal);
        TextView txtFecha_Lle = view.findViewById(R.id.txtFecha_Lle);
        TextView txtNomBarco_viaje = view.findViewById(R.id.txtNomBarco_viaje);
        TextView txtNomPatron_viaje = view.findViewById(R.id.txtNomPatron_viaje);
        TextView txtNomSocio_viaje = view.findViewById(R.id.txtNomSocio_viaje);
        TextView txtId_estado_viaje = view.findViewById(R.id.txtEst_viaje);

        txtId_viaje.setText(arrayViajes.get(position).getId_viaje());
        txtDestino.setText(arrayViajes.get(position).getDestino());
        txtFecha_Sal.setText(arrayViajes.get(position).getFecha_salida());
        txtFecha_Lle.setText(arrayViajes.get(position).getFecha_llegada());
        txtNomBarco_viaje.setText(arrayViajes.get(position).getId_barco());
        txtNomPatron_viaje.setText(arrayViajes.get(position).getId_patron());
        txtNomSocio_viaje.setText(arrayViajes.get(position).getId_socio());
        txtId_estado_viaje.setText(arrayViajes.get(position).getId_estado_viaje());

        return view;
    }
}
