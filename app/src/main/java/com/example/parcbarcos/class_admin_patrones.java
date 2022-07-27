package com.example.parcbarcos;

public class class_admin_patrones {
    private String id_patron;
    private String nom_patron;
    private String ape_patron;
    private String tel_patron;
    private String em_patron;
    private String id_estado_patron;

    public class_admin_patrones() {
    }

    public class_admin_patrones(String id_patron, String nom_patron, String ape_patron, String tel_patron, String em_patron, String id_estado_patron) {
        this.id_patron = id_patron;
        this.nom_patron = nom_patron;
        this.ape_patron = ape_patron;
        this.tel_patron = tel_patron;
        this.em_patron = em_patron;
        this.id_estado_patron = id_estado_patron;
    }

    public String getId_patron() {
        return id_patron;
    }

    public void setId_patron(String id_patron) {
        this.id_patron = id_patron;
    }

    public String getNom_patron() {
        return nom_patron;
    }

    public void setNom_patron(String nom_patron) {
        this.nom_patron = nom_patron;
    }

    public String getApe_patron() {
        return ape_patron;
    }

    public void setApe_patron(String ape_patron) {
        this.ape_patron = ape_patron;
    }

    public String getTel_patron() {
        return tel_patron;
    }

    public void setTel_patron(String tel_patron) {
        this.tel_patron = tel_patron;
    }

    public String getEm_patron() {
        return em_patron;
    }

    public void setEm_patron(String em_patron) {
        this.em_patron = em_patron;
    }

    public String getId_estado_patron() {
        return id_estado_patron;
    }

    public void setId_estado_patron(String id_estado_patron) {
        this.id_estado_patron = id_estado_patron;
    }
}
