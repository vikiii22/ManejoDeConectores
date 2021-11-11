package com.connectores.app;

import com.connectores.model.Login;
import com.connectores.mysql.LoginBaseDatos;

import java.sql.SQLException;
import java.util.Scanner;

public class MueblesJuan {
    public static void main(String[] args) throws SQLException {
        LoginBaseDatos lb = new LoginBaseDatos();
        if (lb.existeUsuario("juan", "Hola")) {
            System.out.println("Bienvenido a muebles Juan");
            int eleccion = 0;
            boolean seguir = true;

            while (seguir) {
                System.out.println("Que desea hoy? \n" +
                        " 1: ver todos los datos\n" +
                        " 2: Crear ususario\n" +
                        " 3: Eliminar usuario\n" +
                        " 4: Modificar usuario\n" +
                        " 10: Salir");
                Scanner sc = new Scanner(System.in);
                eleccion = sc.nextInt();

                switch (eleccion) {
                    case 1:
                        lb.verTodo();
                        break;
                    case 2:
                        System.out.print("Introduce el nombre: ");
                        String nombre = sc.next();
                        System.out.println("------------");
                        System.out.print("Introduce la contraseña: ");
                        String password = sc.next();
                        System.out.println("------------");
                        System.out.print("Introduce el pais: ");
                        String pais = sc.next();
                        System.out.println("------------");
                        System.out.print("Introduce la edad: ");
                        int edad = sc.nextInt();

                        lb.crearUsuario(nombre, password, pais, edad);
                        break;
                    case 3:
                        System.out.print("Introduce el id del usuario a borrar: ");
                        int id = sc.nextInt();
                        lb.eliminarUsuario(id);
                        break;
                    case 4:
                        System.out.println("Modifica usuario");
                        break;
                    case 10:
                        seguir = false;
                        break;
                    default:
                        System.out.println("No válido");
                        break;
                }
            }
        } else {
            System.out.println("Hasta luego");
        }
    }
}
