package com.example.parcbarcos;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        return super.getView(position, convertView, parent);
    }
}
