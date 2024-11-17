package ClasesP1;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona los usuarios registrados en el sistema.
 */
public class GestorUsuarios {

    // Lista para almacenar los usuarios registrados
    private List<Jugador> usuarios;
    private static final String FILE_USUARIOS = "data/usuarios.txt";

    /**
     * Constructor que inicializa la lista de usuarios y carga los datos desde el archivo.
     */
    public GestorUsuarios() {
        this.usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    /**
     * Menú interactivo para gestionar usuarios.
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
                case 1:
                    darDeAltaUsuario(scanner);
                    break;
                case 2:
                    modificarUsuario(scanner);
                    break;
                case 3:
                    listarUsuarios();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    /**
    * Método para buscar un usuario por su correo electrónico.
    * @param correo El correo del usuario a buscar.
    * @return El usuario encontrado, o null si no existe.
    */
    public Jugador buscarUsuario(String correo) {
        for (Jugador jugador : usuarios) {
            if (jugador.getCorreo().equals(correo)) {
                return jugador;
            }
        }
        return null; // Usuario no encontrado
    }

    /**
     * Método para dar de alta un usuario nuevo.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void darDeAltaUsuario(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimientoStr = scanner.nextLine();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
            Jugador nuevoJugador = new Jugador(nombre, apellidos, fechaNacimiento, correo);

            if (darDeAlta(nuevoJugador)) {
                System.out.println("Usuario registrado con éxito.");
            } else {
                System.out.println("El usuario ya está registrado.");
            }
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha inválido. Use el formato dd/MM/yyyy.");
        }
    }

    /**
     * Método para modificar la información de un usuario existente.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void modificarUsuario(Scanner scanner) {
        System.out.print("Correo del usuario a modificar: ");
        String correo = scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Nuevos apellidos: ");
        String nuevosApellidos = scanner.nextLine();
        System.out.print("Nueva fecha de nacimiento (dd/MM/yyyy): ");
        String nuevaFechaNacimiento = scanner.nextLine();

        if (modificarUsuario(correo, nuevoNombre, nuevosApellidos, nuevaFechaNacimiento)) {
            System.out.println("Usuario modificado con éxito.");
        } else {
            System.out.println("Usuario no encontrado o error en el formato.");
        }
    }

    /**
     * Método para dar de alta a un usuario.
     * Verifica si el correo ya está registrado para evitar duplicados.
     * @param nuevoJugador El nuevo jugador a añadir.
     * @return true si el usuario se registra con éxito, false si ya está registrado.
     */
    public boolean darDeAlta(Jugador nuevoJugador) {
        for (Jugador jugador : usuarios) {
            if (jugador.getCorreo().equals(nuevoJugador.getCorreo())) {
                return false; // Usuario ya registrado
            }
        }
        usuarios.add(nuevoJugador);
        return true; // Usuario registrado
    }

    /**
     * Método para modificar la información de un usuario existente.
     * @param correo El correo del usuario a modificar.
     * @param nuevoNombre El nuevo nombre del usuario.
     * @param nuevosApellidos Los nuevos apellidos del usuario.
     * @param nuevaFechaNacimiento La nueva fecha de nacimiento en formato dd/MM/yyyy.
     * @return true si la modificación es exitosa, false si el usuario no se encuentra o si hay un error en el formato de fecha.
     */
    public boolean modificarUsuario(String correo, String nuevoNombre, String nuevosApellidos, String nuevaFechaNacimiento) {
        for (Jugador jugador : usuarios) {
            if (jugador.getCorreo().equals(correo)) {
                jugador.setNombre(nuevoNombre);
                jugador.setApellidos(nuevosApellidos);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaNacimiento = dateFormat.parse(nuevaFechaNacimiento);
                    jugador.setFechaNacimiento(fechaNacimiento);
                    return true; // Modificación correcta
                } catch (ParseException ex) {
                    System.out.println("Error: Formato de fecha inválido. Use el formato dd/MM/yyyy");
                }
            }
        }
        return false; // Usuario no encontrado
    }

    /**
     * Método para listar los usuarios registrados.
     */
    public void listarUsuarios() {
        for (Jugador jugador : usuarios) {
            System.out.println(jugador.toString());
        }
    }

   /**
 * Método para guardar los usuarios actuales en la base de datos.
 */
public void guardarUsuarios() {
    try {
        UserDAO userDAO = new UserDAO(); // Instancia del DAO
        for (Jugador jugador : usuarios) {
            UserDTO userDTO = new UserDTO();
            userDTO.setNombre(jugador.getNombre());
            userDTO.setApellidos(jugador.getApellidos());
            userDTO.setCorreo(jugador.getCorreo());
            userDTO.setFechaNacimiento(jugador.getFechaNacimiento());

            // Llama al método para guardar o actualizar el usuario
            userDAO.saveOrUpdateUser(userDTO);
        }
    } catch (Exception e) {
        System.out.println("Error al guardar los usuarios en la base de datos: " + e.getMessage());
    }
}


    /**
 * Método para cargar los usuarios desde la base de datos.
 */
public void cargarUsuarios() {
    try {
        UserDAO userDAO = new UserDAO(); // Instancia del DAO
        List<UserDTO> usuariosDTO = userDAO.getAllUsers(); // Obtiene los usuarios de la BD

        // Convierte los UserDTO a objetos Jugador y los agrega a la lista
        for (UserDTO userDTO : usuariosDTO) {
            Jugador jugador = new Jugador(
                userDTO.getNombre(),
                userDTO.getApellidos(),
                userDTO.getFechaNacimiento(),
                userDTO.getCorreo()
            );
            usuarios.add(jugador);
        }
    } catch (Exception e) {
        System.out.println("Error al cargar los usuarios desde la base de datos: " + e.getMessage());
    }
}

}
