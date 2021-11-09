package com.connectores.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static DatabaseConnection INSTANCE;
    public String value;

    public static DatabaseConnection getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseConnection();
        }
        return INSTANCE;
    }

    public static Connection getConnection() throws IOException {
        Properties properties=new Properties();
        properties.load(new FileReader("usuario.properties"));
        String url=properties.getProperty("url", "default");
        String user=properties.getProperty("username", "default");
        String password=properties.getProperty("password", "default");

        Connection con=null;

        try {
            con = DriverManager.getConnection(url, user, password);
            if (con != null){
                System.out.println("Conectado a la APP");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }
}
