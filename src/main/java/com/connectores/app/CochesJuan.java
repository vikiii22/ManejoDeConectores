package com.connectores.app;

import com.connectores.model.Login;
import com.connectores.mysql.LoginBaseDatos;

import java.sql.SQLException;
import java.util.Scanner;

/*app gestión de compras de coches y envío tipo Tesla*/

public class CochesJuan {
    public static void main(String[] args) throws SQLException {
        LoginBaseDatos lb = new LoginBaseDatos();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasenya = sc.next();

        if (lb.existeUsuario(usuario, contrasenya)) {
            System.out.println("Bienvenido a coches Juan");
            int eleccion = 0;
            boolean seguir = true;

            while (seguir) {
                System.out.println("Que desea hoy? \n" +
                        " 1: ver todos los datos\n" +
                        " 2: Crear ususario\n" +
                        " 3: Eliminar usuario\n" +
                        " 4: Modificar usuario\n" +
                        " 5: Media de edad de usuarios\n" +
                        " 6: Usuarios registrados\n" +
                        " 7: Mi zona de envio\n" +
                        " 8: Modificar Vehiculo seleccionado\n" +
                        " 9: Localizar mi ID\n" +
                        " 10: Buscar por ID\n" +
                        " 11: Salir");

                eleccion = sc.nextInt();

                switch (eleccion) {
                    case 1:
                        lb.verTodo();
                        break;
                    case 2:
                        System.out.print("Introduce el nombre: ");
                        sc.nextLine();
                        String nombre = sc.nextLine();
                        System.out.println("------------");
                        System.out.print("Introduce la contraseña: ");
                        String password = sc.nextLine();
                        System.out.println("------------");
                        System.out.print("Introduce el pais: ");
                        String pais = sc.nextLine();
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
                        /*Añadir sc con cada campo para añadirlo el usuario*/
                        lb.modificarUsuario(11, "Julian", "España", 22, "contraseña");
                        break;
                    case 5:
                        lb.edadMedia();
                        break;
                    case 6:
                        lb.usuariosRegistrados();
                        break;
                    case 7:
                        System.out.print("Nombre: ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        lb.miZonaDeEnvio(name);
                        try {
                            Thread.sleep(3000); //Espero tres segundos para que al usuario le de tiempo de ver donde será enviado
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 8:
                        System.out.print("Ingresa tu id: ");
                        int iduser = sc.nextInt();
                        System.out.print("Ingresa el modelo que deseas: ");
                        sc.nextLine();
                        String modelo = sc.nextLine();
                        lb.modificarVehiculoSeleccionado(iduser, modelo);
                        break;
                    case 9:
                        System.out.print("Introduce tu nombre: ");
                        sc.nextLine();
                        String miNombre = sc.nextLine();
                        lb.localizarMiID(miNombre);
                        break;
                    case 10:
                        System.out.print("Introduce tu id: ");
                        int miId = sc.nextInt();
                        lb.busquedaPorID(miId);
                        break;
                    case 11:
                        seguir = false;
                        break;
                    default:
                        System.out.println("No válido");
                        break;
                }
            }
        } else if (lb.existeUsuarioNormal(usuario, contrasenya)) {
            int eleccion = 0;
            boolean seguir = true;
            while (seguir) {
                System.out.println("Que desea hoy? \n" +
                        " 1: ver todos los datos\n" +
                        " 11: Salir");

                eleccion = sc.nextInt();

                switch (eleccion) {
                    case 1:
                        lb.verTodo();
                        break;
                    case 2:
                        System.out.println("Acción");
                        break;
                    case 5:
                        seguir = false;
                        break;
                }
            }
        } else {
            System.out.println("Usuario no administrador o no registrado, hasta luego");
            System.out.println("Desea registrar un nuevo usuario?");
            sc.nextLine();
            String nuevo=sc.nextLine();
            if (nuevo.equals("Si") || nuevo.equals("si")){
                System.out.print("Introduce el nombre: ");
                String nombre = sc.nextLine();
                System.out.println("------------");
                System.out.print("Introduce la contraseña: ");
                String password = sc.nextLine();
                System.out.println("------------");
                System.out.print("Introduce el pais: ");
                String pais = sc.nextLine();
                System.out.println("------------");
                System.out.print("Introduce la edad: ");
                int edad = sc.nextInt();

                lb.crearUsuario(nombre, password, pais, edad);
            }else {
                System.out.println("De acuerdo, hasta luego");
            }
        }
    }
}
