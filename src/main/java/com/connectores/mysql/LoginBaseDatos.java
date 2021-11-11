package com.connectores.mysql;

import com.connectores.model.Login;
import com.connectores.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginBaseDatos {
    public static Connection con;

    static {
        try {
            con = DatabaseConnection.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Login> getLogins() throws SQLException {
        String sql = "SELECT * FROM manejoconectores.users";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Login> logins = new ArrayList<>();

        while (rs.next()) {
            Login login = new Login();
            login.setIdUser(rs.getInt("idUser"));
            login.setName(rs.getString("name"));
            login.setPais(rs.getString("pais"));
            login.setEdad(rs.getInt("edad"));
            login.setPassword(rs.getString("password"));
            logins.add(login);
        }
        return logins;
    }

    public void verTodo() throws SQLException {
        String sql = "SELECT * FROM manejoconectores.users";
        PreparedStatement statement = con.prepareStatement(sql);
        for (Login login : getLogins()) {
            System.out.println("---------------------------------");
            System.out.println("|" + login.getIdUser() + " " + login.getName() + " " + login.getPais() + " " + login.getEdad() + " " + login.getPassword());
            System.out.println("---------------------------------");
        }
        statement.executeQuery(sql);
    }

    public void crearUsuario(String user, String password, String pais, int edad) throws SQLException {
        String sql = "INSERT INTO manejoconectores.users(name, password, pais, edad)\n" +
                "\tVALUES(\"" + user + "\", \"" + password + "\", \"" + pais + "\", " + edad + ");";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    public Boolean existeUsuario(String user, String password) throws SQLException {
        boolean existe = false;
        for (Login login : getLogins()) {
            if (login.getName().equals(user) && login.getPassword().equals(password)) {
                existe = true;
            }
        }
        return existe;
    }

    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM manejoconectores.users WHERE idUser = " + id + ";";
        for (Login login : getLogins()) {
            if (login.getIdUser() == id) {
                PreparedStatement statement = con.prepareStatement(sql);
                statement.executeUpdate(sql);
            }
        }
        System.out.println("Eliminado con éxito");
    }

    public void modificarUsuario(int id, String name, String pais, int edad, String password) throws SQLException {
        String sql = "UPDATE manejoconectores.users SET name=\"" + name + "\", pais=\"" + pais + "\", edad=" + edad + ", password=\"" + password + "\"\n" +
                "\tWHERE idUser=" + id + ";";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.executeUpdate(sql);
        System.out.println("Usuario modificado con éxito");
    }

    public void edadMedia() throws SQLException {
        String sql = "SELECT avg(edad) FROM manejoconectores.users;";
        Statement statement=con.createStatement();
        ResultSet rs=statement.executeQuery(sql);
        if (rs.next()){
            System.out.println("La media de edad de los compradores es: " + rs.getInt(1) + " años");
        }
    }

    public void usuariosRegistrados() throws SQLException {
        String sql="SELECT COUNT(idUser) FROM manejoconectores.users;";
        Statement statement= con.createStatement();
        ResultSet rs=statement.executeQuery(sql);
        if (rs.next()){
            System.out.println("La cantidad de usuarios registrados es: " + rs.getInt(1));
        }
    }
}
