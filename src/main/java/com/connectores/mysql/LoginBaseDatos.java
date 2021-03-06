package com.connectores.mysql;

import com.connectores.model.Login;
import com.connectores.util.DatabaseConnection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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

    public List<Login> getAdmins() throws SQLException {
        String sql = "SELECT * FROM manejoconectores.administradores";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Login> logins = new ArrayList<>();

        while (rs.next()) {
            Login login = new Login();
            login.setName(rs.getString("Nombre"));
            login.setPassword(rs.getString("Password"));
            logins.add(login);
        }
        return logins;
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
            login.setModelo(rs.getString("cocheSeleccionado"));
            logins.add(login);
        }
        return logins;
    }

    public void verTodo() {
        String sql = "SELECT * FROM manejoconectores.users";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            for (Login login : getLogins()) {
                System.out.println("-----------------------------------------------");
                System.out.println("|" + login.getIdUser() + " " + login.getName() + " " + login.getPais() + " " + login.getEdad() + " " + login.getPassword() + " " + login.getModelo());
                System.out.println("-----------------------------------------------");
            }
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verTodoUsuario(String userName) {
        String sql = "SELECT * FROM manejoconectores.users WHERE name=\"" + userName + "\";";
        try {
            Statement statement= con.createStatement();
            ResultSet rs=statement.executeQuery(sql);

            if (rs.next()){
                System.out.println("Nombre: " + rs.getString(2)+"\n" +
                        "Pa??s: " + rs.getString(3)+"\n" +
                        "Edad: " + rs.getInt(4) + "\n" +
                        "Contrase??a: " + rs.getString(5) + "\n" +
                        "Coche seleccionado: " + rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearUsuario(String user, String password, String pais, int edad) {
        String sql = "INSERT INTO manejoconectores.users(name, password, pais, edad)\n" +
                "\tVALUES(\"" + user + "\", \"" + password + "\", \"" + pais + "\", " + edad + ");";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.executeUpdate(sql);
            int lineas=statement.executeUpdate();
            if (lineas==4){
                con.commit();
            }else{
                con.rollback();
            }
        } catch (SQLException e) {
            System.out.println("No se ha podido crear el usuario");
        }
    }

    public Boolean existeAdmin(String user, String password) {
        boolean existe = false;
        try {
            for (Login login : getAdmins()) {
                if (login.getName().equals(user) && login.getPassword().equals(password)) {
                    existe = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public Boolean existeUsuarioNormal(String user, String password) {
        boolean existe = false;
        try {
            for (Login login : getLogins()) {
                if (login.getName().equals(user) && login.getPassword().equals(password)) {
                    existe = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existe;
    }

    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM manejoconectores.users WHERE idUser = " + id + ";";
        try {
            for (Login login : getLogins()) {
                if (login.getIdUser() == id) {
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate(sql);
                    int lineas=statement.executeUpdate();
                    if (lineas==1){
                        con.commit();
                    }else{
                        con.rollback();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Usuario no existente");
        }
        System.out.println("Eliminado con ??xito");
    }

    public void modificarUsuario(int id, String name, String pais, int edad, String password) throws SQLException {
        String sql = "UPDATE manejoconectores.users SET name=\"" + name + "\", pais=\"" + pais + "\", edad=" + edad + ", password=\"" + password + "\"\n" +
                "\tWHERE idUser=" + id + ";";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.executeUpdate(sql);
        System.out.println("Usuario modificado con ??xito");
    }

    public void edadMedia() {
        String sql = "SELECT avg(edad) FROM manejoconectores.users;";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println("La media de edad de los compradores es: " + rs.getInt(1) + " a??os");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void usuariosRegistrados() {
        String sql = "SELECT COUNT(idUser) FROM manejoconectores.users;";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println("La cantidad de usuarios registrados es: " + rs.getInt(1));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void miZonaDeEnvio(String name) {
        String sql = "SELECT pais FROM manejoconectores.users WHERE name=\"" + name + "\";";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Tu zona de env??o es: " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarTodo() {
        String sql="TRUNCATE TABLE manejoconectores.users";
        try {
            PreparedStatement statement= con.prepareStatement(sql);
            statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("No se ha podido eliminar");
        }
    }

    public void modificarVehiculoSeleccionado(int id, String modelo) {
        String sql = "UPDATE manejoconectores.users SET cocheSeleccionado=\"" + modelo + "\"\n" +
                "\tWHERE idUser=" + id + ";";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.executeUpdate(sql);
            System.out.println("Veh??culo modificado con ??xtio");
        } catch (SQLException e) {
            System.out.println("No se ha podido modificar el veh??culo");
        }
    }

    public void localizarMiID(String nombre) {
        String sql = "SELECT idUser FROM manejoconectores.users WHERE name=\"" + nombre + "\"";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Tu ID es: " + rs.getString(1));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void busquedaPorID(int id) {
        String sql = "SELECT name, pais, edad, password, cocheSeleccionado FROM manejoconectores.users WHERE idUser=" + id + ";";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Nombre: " + rs.getString(1) + "\n" +
                        "Pa??s: " + rs.getString(2) + "\n" +
                        "Edad: " + rs.getInt(3) + "\n" +
                        "Pass: " + rs.getString(4) + "\n" +
                        "Coche: " + rs.getString(5));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void exportarAJson(){
        try {
            Gson gson=new Gson();
            List<Login>logins=new ArrayList<>();
            BufferedWriter bw=new BufferedWriter(new FileWriter(".\\usuarios.json"));
            for (Login login:getLogins()){
                Login usu=new Login(login.getIdUser(), login.getName(), login.getPais(), login.getEdad(), login.getPassword(), login.getModelo());
                logins.add(usu);
            }
            gson.toJson(logins, bw);
            bw.close();
            System.out.println("Lista exportada");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
