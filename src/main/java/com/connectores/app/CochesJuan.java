package com.connectores.app;

import com.connectores.mysql.LoginBaseDatos;

import java.sql.SQLException;
import java.util.Scanner;

/*app gestión de compras de coches y envío tipo Tesla sensillo*/

public class CochesJuan {
    public static void main(String[] args) throws SQLException {
        LoginBaseDatos lb = new LoginBaseDatos();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasenya = sc.next();

        if (lb.existeAdmin(usuario, contrasenya)) {
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
                        " 7: Modificar Vehiculo seleccionado\n" +
                        " 8: Buscar por ID\n" +
                        " 9: Salir\n" +
                        " 10: Eliminar todo\n" +
                        " 11: Exportar lista a JSON");

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
                        System.out.print("Introduce tu id: ");
                        int idAModificar = sc.nextInt();
                        System.out.print("Introduce el nombre: ");
                        sc.nextLine();
                        String nombreAModificar = sc.nextLine();
                        System.out.print("Introduce el país: ");
                        String paisAModificar = sc.nextLine();
                        System.out.print("Introduce tu edad: ");
                        int edadAModificar = sc.nextInt();
                        System.out.print("Introduce tu contraseña nueva: ");
                        sc.nextLine();
                        String contrasenyaAModificar = sc.nextLine();

                        lb.modificarUsuario(idAModificar, nombreAModificar, paisAModificar, edadAModificar, contrasenyaAModificar);
                        break;
                    case 5:
                        lb.edadMedia();
                        break;
                    case 6:
                        lb.usuariosRegistrados();
                        break;
                    case 7:
                        System.out.print("Ingresa id del usuario: ");
                        int iduser = sc.nextInt();
                        System.out.print("Ingresa el modelo que deseas: ");
                        sc.nextLine();
                        String modelo = sc.nextLine();
                        lb.modificarVehiculoSeleccionado(iduser, modelo);
                        break;
                    case 8:
                        System.out.print("Introduce el id: ");
                        int miId = sc.nextInt();
                        lb.busquedaPorID(miId);
                        break;
                    case 9:
                        seguir = false;
                        System.out.println("Hasta luego");
                        break;
                    case 10:
                        System.out.print("¿Seguro?: ");
                        sc.nextLine();
                        String opcion = sc.nextLine();
                        if (opcion.toLowerCase().equals("si")) {
                            lb.eliminarTodo();
                        }else{
                            System.out.println("Mejor");
                        }
                        break;
                    case 11:
                        lb.exportarAJson();
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
                        " 1: ver todos tus datos\n" +
                        " 2: Mi zona de envío\n" +
                        " 3: Localizar mi ID\n" +
                        " 4: Salir");

                eleccion = sc.nextInt();

                switch (eleccion) {
                    case 1:
                        lb.verTodoUsuario(usuario);
                        break;
                    case 2:
                        lb.miZonaDeEnvio(usuario);
                        try {
                            Thread.sleep(3000); //Espero tres segundos para que al usuario le de tiempo de ver donde será enviado, lo pongo solo para mostrarlo por consola
                            //para que no se vea el menú tan rápido.
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        lb.localizarMiID(usuario);
                        break;
                    case 4:
                        seguir = false;
                        System.out.println("Hasta luego");
                        break;
                }
            }
        } else {
            System.out.println("Usuario no administrador o no registrado, hasta luego");
            System.out.println("Desea registrar un nuevo usuario?");
            sc.nextLine();
            String nuevo = sc.nextLine();
            if (nuevo.equals("Si") || nuevo.equals("si")) {
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
            } else {
                System.out.println("De acuerdo, hasta luego");
            }
        }
    }
}
