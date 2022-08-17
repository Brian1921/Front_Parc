package com.example.parcbarcos;

public class class_admin_usuarios {
    private String id_usuario;
    private String usuario;
    private String descrip_rol;

    public class_admin_usuarios() {
    }

    public class_admin_usuarios(String id_usuario, String usuario, String descrip_rol) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.descrip_rol = descrip_rol;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescrip_rol() {
        return descrip_rol;
    }

    public void setDescrip_rol(String descrip_rol) {
        this.descrip_rol = descrip_rol;
    }
}
