package com.connectores.model;

public class Login {
    int idUser;
    String name;
    String pais;
    int edad;
    String password;

    public Login(int idUser, String name, String pais, int edad, String password) {
        this.idUser = idUser;
        this.name = name;
        this.pais = pais;
        this.edad = edad;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
