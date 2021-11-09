package com.connectores.model;

public class Login {
    int idUser;
    String name;
    String pais;
    int edad;

    public Login(int idUser, String name, String pais, int edad) {
        this.idUser = idUser;
        this.name = name;
        this.pais = pais;
        this.edad = edad;
    }

    public Login() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
