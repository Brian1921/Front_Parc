package com.example.parcbarcos;

public class class_patron_viajes {

    private String id_viaje;
    private String fecha_salida;
    private String fecha_llegada;
    private String destino;
    private String id_barco;
    private String id_socio;

    public class_patron_viajes() {
    }

    public class_patron_viajes(String id_viaje, String fecha_salida, String fecha_llegada, String destino, String id_barco, String id_socio) {
        this.id_viaje = id_viaje;
        this.fecha_salida = fecha_salida;
        this.fecha_llegada = fecha_llegada;
        this.destino = destino;
        this.id_barco = id_barco;
        this.id_socio = id_socio;
    }

    public String getId_viaje() {
        return id_viaje;
    }

    public void setId_viaje(String id_viaje) {
        this.id_viaje = id_viaje;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getId_barco() {
        return id_barco;
    }

    public void setId_barco(String id_barco) {
        this.id_barco = id_barco;
    }

    public String getId_socio() {
        return id_socio;
    }

    public void setId_socio(String id_socio) {
        this.id_socio = id_socio;
    }
}
