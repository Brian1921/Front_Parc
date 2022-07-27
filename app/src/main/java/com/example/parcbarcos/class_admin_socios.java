package com.example.parcbarcos;

public class class_admin_socios {
    private String id_socio;
    private String nom_socio;
    private String ape_socio;
    private String tel_socio;
    private String em_socio;
    private String id_estado_socio;

    public class_admin_socios() {
    }

    public class_admin_socios(String id_socio, String nom_socio, String ape_socio, String tel_socio, String em_socio, String id_estado_socio) {
        this.id_socio = id_socio;
        this.nom_socio = nom_socio;
        this.ape_socio = ape_socio;
        this.tel_socio = tel_socio;
        this.em_socio = em_socio;
        this.id_estado_socio = id_estado_socio;
    }

    public String getId_socio() {
        return id_socio;
    }

    public void setId_socio(String id_socio) {
        this.id_socio = id_socio;
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

    public String getTel_socio() {
        return tel_socio;
    }

    public void setTel_socio(String tel_socio) {
        this.tel_socio = tel_socio;
    }

    public String getEm_socio() {
        return em_socio;
    }

    public void setEm_socio(String em_socio) {
        this.em_socio = em_socio;
    }

    public String getId_estado_socio() {
        return id_estado_socio;
    }

    public void setId_estado_socio(String id_estado_socio) {
        this.id_estado_socio = id_estado_socio;
    }
}
