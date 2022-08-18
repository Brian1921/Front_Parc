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

public class Adapter_class_patron_viajes extends ArrayAdapter<class_patron_viajes> {
    Context context;
    List<class_patron_viajes> arrayViajes;

    public Adapter_class_patron_viajes(@NonNull Context context, List<class_patron_viajes> arrayViajes) {
        super(context, R.layout.list_viajes_patrones, arrayViajes);
        this.context=context;
        this.arrayViajes=arrayViajes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_viajes_patrones, null, true);

        TextView txtId_viaje = view.findViewById(R.id.txtId_viaje_pa);
        TextView txtDestino = view.findViewById(R.id.txtDestino_pa);
        TextView txtFecha_Sal = view.findViewById(R.id.txtFecha_Sal_pa);
        TextView txtFecha_Lle = view.findViewById(R.id.txtFecha_Lle_pa);
        TextView txtNomBarco_viaje = view.findViewById(R.id.txtNomBarco_viaje_pa);
        TextView txtNomSocio_viaje = view.findViewById(R.id.txtNomSocio_viaje_pa);

        txtId_viaje.setText(arrayViajes.get(position).getId_viaje());
        txtDestino.setText(arrayViajes.get(position).getDestino());
        txtFecha_Sal.setText(arrayViajes.get(position).getFecha_salida());
        txtFecha_Lle.setText(arrayViajes.get(position).getFecha_llegada());
        txtNomBarco_viaje.setText(arrayViajes.get(position).getId_barco());
        txtNomSocio_viaje.setText(arrayViajes.get(position).getId_socio());

        return  view;
    }
}
