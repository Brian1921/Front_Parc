package com.example.parcbarcos;

public class class_admin_barcos {
    private String id_barco;
    private String nom_barco;
    private String id_amarre;
    private String nom_socio;
    private String ape_socio;
    private String id_estado_barco;

    public class_admin_barcos() {
    }

    public class_admin_barcos(String id_barco, String nom_barco, String id_amarre, String nom_socio, String ape_socio, String id_estado_barco) {
        this.id_barco = id_barco;
        this.nom_barco = nom_barco;
        this.id_amarre = id_amarre;
        this.nom_socio = nom_socio;
        this.ape_socio = ape_socio;
        this.id_estado_barco = id_estado_barco;
    }

    public String getId_barco() {
        return id_barco;
    }

    public void setId_barco(String id_barco) {
        this.id_barco = id_barco;
    }

    public String getNom_barco() {
        return nom_barco;
    }

    public void setNom_barco(String nom_barco) {
        this.nom_barco = nom_barco;
    }

    public String getId_amarre() {
        return id_amarre;
    }

    public void setId_amarre(String id_amarre) {
        this.id_amarre = id_amarre;
    }

    public String getNom_socio() {
        return nom_socio;
    }

    public void setNom_socio(String nom_socio) {
        this.nom_socio = nom_socio;
    }

    public String getApe_socio() {
        return ape_socio;
    }

    public void setApe_socio(String ape_socio) {
        this.ape_socio = ape_socio;
    }

    public String getId_estado_barco() {
        return id_estado_barco;
    }

    public void setId_estado_barco(String id_estado_barco) {
        this.id_estado_barco = id_estado_barco;
    }


}
