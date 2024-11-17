package business;

import data.dao.JugadorDAO;
import Jugador;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona los usuarios registrados en el sistema utilizando DAOs y DTOs.
 */
public class GestorUsuarios {

    private final JugadorDAO jugadorDAO;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructor que inicializa el gestor con el DAO.
     */
    public GestorUsuarios() {
        this.jugadorDAO = new JugadorDAO();
    }

    /**
     * Menú interactivo para gestionar usuarios.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    public void menuGestionUsuarios(Scanner scanner) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Menú de Gestión de Usuarios ---");
            System.out.println("1. Dar de alta un usuario");
            System.out.println("2. Modificar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> darDeAltaUsuario(scanner);
                case 2 -> modificarUsuario(scanner);
                case 3 -> listarUsuarios();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     *
     * @param scanner Objeto Scanner para leer los datos del usuario.
     */
    private void darDeAltaUsuario(Scanner scanner) {
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Apellidos: ");
            String apellidos = scanner.nextLine();
            System.out.print("Correo electrónico: ");
            String correo = scanner.nextLine();
            System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
            Date fechaNacimiento = DATE_FORMAT.parse(scanner.nextLine());

            Jugador jugador = new Jugador(nombre, apellidos, fechaNacimiento, correo);
            jugadorDAO.insertarJugador(jugador);
            System.out.println("Usuario registrado con éxito.");
        } catch (ParseException e) {
            System.out.println("Error en el formato de la fecha. Use dd/MM/yyyy.");
        } catch (SQLException e) {
            System.err.println("Error al registrar el usuario: " + e.getMessage());
        }
    }

    /**
     * Modifica la información de un usuario existente.
     *
     * @param scanner Objeto Scanner para leer los datos del usuario.
     */
    private void modificarUsuario(Scanner scanner) {
        try {
            System.out.print("Correo del usuario a modificar: ");
            String correo = scanner.nextLine();

            Jugador jugador = jugadorDAO.buscarJugadorPorCorreo(correo);
            if (jugador == null) {
                System.out.println("Usuario no encontrado.");
                return;
            }

            System.out.print("Nuevo nombre: ");
            jugador.setNombre(scanner.nextLine());
            System.out.print("Nuevos apellidos: ");
            jugador.setApellidos(scanner.nextLine());
            System.out.print("Nueva fecha de nacimiento (dd/MM/yyyy): ");
            Date nuevaFechaNacimiento = DATE_FORMAT.parse(scanner.nextLine());

            jugador.setFechaNacimiento(nuevaFechaNacimiento);
            jugadorDAO.actualizarJugador(jugador);
            System.out.println("Usuario modificado con éxito.");
        } catch (ParseException e) {
            System.out.println("Error en el formato de la fecha. Use dd/MM/yyyy.");
        } catch (SQLException e) {
            System.err.println("Error al modificar el usuario: " + e.getMessage());
        }
    }

    /**
     * Lista todos los usuarios registrados en la base de datos.
     */
    public void listarUsuarios() {
        try {
            List<Jugador> jugadores = jugadorDAO.obtenerJugadores();
            if (jugadores.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
            } else {
                System.out.println("\n--- Lista de Usuarios ---");
                jugadores.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los usuarios: " + e.getMessage());
        }
    }
}
