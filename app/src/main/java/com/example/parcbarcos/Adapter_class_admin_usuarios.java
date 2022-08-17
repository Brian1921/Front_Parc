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

public class Adapter_class_admin_usuarios extends ArrayAdapter<class_admin_usuarios>{
    Context context;
    List<class_admin_usuarios> arrayUsuarios;



    public Adapter_class_admin_usuarios(@NonNull Context context, List<class_admin_usuarios>arrayUsuarios){
        super(context, R.layout.list_usuarios, arrayUsuarios);
        this.context=context;
        this.arrayUsuarios=arrayUsuarios;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_usuarios, null,true);

        TextView id_usuario= view.findViewById(R.id.txtId_usuario);
        TextView usuario = view.findViewById(R.id.txtUsuario);
        TextView descrip_rol = view.findViewById(R.id.txtDescrip_rol);


        id_usuario.setText(arrayUsuarios.get(position).getId_usuario());
        usuario.setText(arrayUsuarios.get(position).getUsuario());
        descrip_rol.setText(arrayUsuarios.get(position).getDescrip_rol());
        return view;
    }


}
