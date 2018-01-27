package com.project.ciaprojects.europresents.com.project.ciaprojects.europresents.firebase.beans;

/**
 * Created by masdi on 26/01/2018.
 */

public class Sorteo {
    private String producto;
    private int participacionesTotales;
    private int participacionesActuales;
    private boolean finalizado;

    public Sorteo() {
    }

    public Sorteo(String producto, int participacionesTotales, int participacionesActuales, boolean finalizado) {
        this.producto = producto;
        this.participacionesTotales = participacionesTotales;
        this.participacionesActuales = participacionesActuales;
        this.finalizado = finalizado;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getParticipacionesTotales() {
        return participacionesTotales;
    }

    public void setParticipacionesTotales(int participacionesTotales) {
        this.participacionesTotales = participacionesTotales;
    }

    public int getParticipacionesActuales() {
        return participacionesActuales;
    }

    public void setParticipacionesActuales(int participacionesActuales) {
        this.participacionesActuales = participacionesActuales;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
