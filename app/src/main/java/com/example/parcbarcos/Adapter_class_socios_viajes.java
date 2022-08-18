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

public class Adapter_class_socios_viajes extends ArrayAdapter<class_socios_viajes> {
    Context context;
    List<class_socios_viajes> arrayViajes;


    public Adapter_class_socios_viajes(@NonNull Context context, List<class_socios_viajes> arrayViajes) {
        super(context, R.layout.list_socios_viajes, arrayViajes);
        this.context=context;
        this.arrayViajes=arrayViajes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_socios_viajes, null, true);

        TextView txtId_viaje = view.findViewById(R.id.txt_Socio_Id_viaje);
        TextView txtFecha_Sal = view.findViewById(R.id.txt_Socio_Fecha_Sal);
        TextView txtFecha_Lle = view.findViewById(R.id.txt_socio_Fecha_Lle);
        TextView txtDestino = view.findViewById(R.id.txt_socio_Destino);
        TextView txtNomBarco_viaje = view.findViewById(R.id.txt_socio_NomBarco_viaje);
        TextView txtNomPatron_viaje = view.findViewById(R.id.txt_socio_NomPatron_viaje);

        txtId_viaje.setText(arrayViajes.get(position).getId_viaje());
        txtFecha_Sal.setText(arrayViajes.get(position).getFecha_salida());
        txtFecha_Lle.setText(arrayViajes.get(position).getFecha_llegada());
        txtDestino.setText(arrayViajes.get(position).getDestino());
        txtNomBarco_viaje.setText(arrayViajes.get(position).getId_barco());
        txtNomPatron_viaje.setText(arrayViajes.get(position).getId_patron());

        return view;
    }
}
