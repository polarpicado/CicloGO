package com.joao.ciclogo.entidad;

public class Rutas {
    private String id;
    private String creador;
    private String nombre;
    private String latitud_inicio;
    private String longitud_inicio;
    private String latitud_final;
    private String longitud_final;
    private String peligrosidad;

    public Rutas(String id, String creador, String nombre, String latitud_inicio, String longitud_inicio, String latitud_final, String longitud_final, String peligrosidad) {
        this.id = id;
        this.creador = creador;
        this.nombre = nombre;
        this.latitud_inicio = latitud_inicio;
        this.longitud_inicio = longitud_inicio;
        this.latitud_final = latitud_final;
        this.longitud_final = longitud_final;
        this.peligrosidad = peligrosidad;
    }

    public Rutas() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLatitud_inicio() {
        return latitud_inicio;
    }

    public void setLatitud_inicio(String latitud_inicio) {
        this.latitud_inicio = latitud_inicio;
    }

    public String getLongitud_inicio() {
        return longitud_inicio;
    }

    public void setLongitud_inicio(String longitud_inicio) {
        this.longitud_inicio = longitud_inicio;
    }

    public String getLatitud_final() {
        return latitud_final;
    }

    public void setLatitud_final(String latitud_final) {
        this.latitud_final = latitud_final;
    }

    public String getLongitud_final() {
        return longitud_final;
    }

    public void setLongitud_final(String longitud_final) {
        this.longitud_final = longitud_final;
    }

    public String getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(String peligrosidad) {
        this.peligrosidad = peligrosidad;
    }
}