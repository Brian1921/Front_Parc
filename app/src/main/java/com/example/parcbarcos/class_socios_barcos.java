package com.example.parcbarcos;

public class class_socios_barcos {
    private String id_barco;
    private String nom_barco;
    private String id_socio;
    private String nom_amarre;

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

    public String getId_socio() {
        return id_socio;
    }

    public void setId_socio(String id_socio) {
        this.id_socio = id_socio;
    }

    public String getNom_amarre() {
        return nom_amarre;
    }

    public void setNom_amarre(String nom_amarre) {
        this.nom_amarre = nom_amarre;
    }

    public class_socios_barcos(String id_barco, String nom_barco, String id_socio, String nom_amarre) {
        this.id_barco = id_barco;
        this.nom_barco = nom_barco;
        this.id_socio = id_socio;
        this.nom_amarre = nom_amarre;
    }

    public class_socios_barcos() {
    }


}
