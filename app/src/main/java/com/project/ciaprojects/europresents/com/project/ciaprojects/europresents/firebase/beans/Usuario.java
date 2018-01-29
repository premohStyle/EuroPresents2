package com.project.ciaprojects.europresents.com.project.ciaprojects.europresents.firebase.beans;

import java.util.List;

/**
 * Created by masdi on 30/01/2018.
 */

public class Usuario {
    private String id;
    private String nickname;
    private String email;
    private String nombre;
    private String direccion;
    private int mondedero;

    private List<Sorteo> sorteosParticipados;

    public Usuario() {

    }

    public Usuario(String id, String nickname, String email, String nombre, String direccion, int mondedero, List<Sorteo> sorteosParticipados) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.nombre = nombre;
        this.direccion = direccion;
        this.mondedero = mondedero;
        this.sorteosParticipados = sorteosParticipados;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getMondedero() {
        return mondedero;
    }

    public void setMondedero(int mondedero) {
        this.mondedero = mondedero;
    }

    public List<Sorteo> getSorteosParticipados() {
        return sorteosParticipados;
    }

    public void setSorteosParticipados(List<Sorteo> sorteosParticipados) {
        this.sorteosParticipados = sorteosParticipados;
    }
}
