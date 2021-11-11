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
        String sql="SELECT * FROM manejoconectores.users";
        Statement statement=con.createStatement();
        ResultSet rs=statement.executeQuery(sql);
        List<Login> logins=new ArrayList<>();

        while (rs.next()){
            Login login=new Login();
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
        String sql="SELECT * FROM manejoconectores.users";
        PreparedStatement statement=con.prepareStatement(sql);
        for (Login login:getLogins()) {
            System.out.println("---------------------------------");
            System.out.println("|"+login.getIdUser() + " " + login.getName() + " " + login.getPais() + " " + login.getEdad() + " " + login.getPassword());
            System.out.println("---------------------------------");
        }
        statement.executeQuery(sql);
    }

    public void userLog(String user) throws SQLException {
        String sql="SELECT * FROM manejoconectores.users WHERE name="+"\"" + user + "\"";
        PreparedStatement statement=con.prepareStatement(sql);
        for (Login login:getLogins()){
            if (user.equals(login.getName())){
                System.out.println("Existe");
            }
        }
        statement.executeQuery(sql);
    }

    public void crearUsuario(String user, String password, String pais, int edad) throws SQLException {
        String sql="INSERT INTO manejoconectores.users(name, password, pais, edad)\n" +
                "\tVALUES(\""+user+"\", \""+password+"\", \""+pais+"\", "+edad+");";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    /**TODO
     * Eliminar usuario
     */

    /**TODO
     * Modificar usuario
     */
}
