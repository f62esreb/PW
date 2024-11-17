package business;

import data.dao.MaterialDAO;
import data.dao.PistaDAO;
import Material;
import Pista;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona las pistas y materiales necesarios para dar servicio utilizando DAOs y DTOs.
 */
public class GestorDePistas {

    // DAOs para manejar las operaciones con la base de datos
    private final PistaDAO pistaDAO;
    private final MaterialDAO materialDAO;

    /**
     * Constructor que inicializa los DAOs.
     */
    public GestorDePistas() {
        this.pistaDAO = new PistaDAO();
        this.materialDAO = new MaterialDAO();
    }

    /**
     * Menú interactivo para gestionar pistas.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    public void menuGestionPistas(Scanner scanner) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Menú de Gestión de Pistas ---");
            System.out.println("1. Crear nueva pista");
            System.out.println("2. Crear nuevo material");
            System.out.println("3. Asociar material a pista");
            System.out.println("4. Listar pistas no disponibles");
            System.out.println("5. Buscar pistas libres");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> crearNuevaPista(scanner);
                case 2 -> crearNuevoMaterial(scanner);
                case 3 -> asociarMaterialAPista(scanner);
                case 4 -> listarPistasNoDisponibles();
                case 5 -> buscarPistasLibres(scanner);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    /**
     * Método para crear una nueva pista y añadirla a la base de datos.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void crearNuevaPista(Scanner scanner) {
        try {
            System.out.print("ID de la pista: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("¿Está disponible? (true/false): ");
            boolean disponible = scanner.nextBoolean();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("¿Es interior? (true/false): ");
            boolean interior = scanner.nextBoolean();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Tamaño (MINIBASKET, ADULTOS, TRES_VS_TRES): ");
            String tamaño = scanner.nextLine().toUpperCase();
            System.out.print("Número máximo de jugadores: ");
            int nMaximo = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Pista pista = new Pista(id, disponible, interior, tamaño, nMaximo);
            pistaDAO.insertarPista(pista);
            System.out.println("Pista creada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear la pista: " + e.getMessage());
        }
    }

    /**
     * Método para crear un nuevo material y añadirlo a la base de datos.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void crearNuevoMaterial(Scanner scanner) {
        try {
            System.out.print("ID del material: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Tipo de material (PELOTAS, CANASTAS, CONOS): ");
            String tipo = scanner.nextLine().toUpperCase();
            System.out.print("¿Es para interior? (true/false): ");
            boolean usoInterior = scanner.nextBoolean();
            scanner.nextLine(); // Limpiar el buffer
            System.out.print("Estado del material (DISPONIBLE, RESERVADO, MAL_ESTADO): ");
            String estado = scanner.nextLine().toUpperCase();

            Material material = new Material(id, tipo, usoInterior, estado);
            materialDAO.insertarMaterial(material);
            System.out.println("Material creado con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al crear el material: " + e.getMessage());
        }
    }

    /**
     * Método para asociar un material a una pista disponible.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void asociarMaterialAPista(Scanner scanner) {
        try {
            System.out.print("ID de la pista: ");
            int pistaId = scanner.nextInt();
            System.out.print("ID del material: ");
            int materialId = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Pista pista = pistaDAO.buscarPistaPorId(pistaId);
            Material material = materialDAO.buscarMaterialPorId(materialId);

            if (pista != null && material != null) {
                pista.asociarMaterial(material); // Método del DTO Pista para asociar
                System.out.println("Material asociado con éxito.");
            } else {
                System.out.println("No se pudo asociar el material.");
            }
        } catch (SQLException e) {
            System.err.println("Error al asociar el material a la pista: " + e.getMessage());
        }
    }

    /**
     * Lista las pistas que no están disponibles.
     */
    private void listarPistasNoDisponibles() {
        try {
            List<Pista> pistas = pistaDAO.obtenerPistas();
            System.out.println("\n--- Pistas No Disponibles ---");
            for (Pista pista : pistas) {
                if (!pista.isDisponibilidad()) {
                    System.out.println(pista);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las pistas: " + e.getMessage());
        }
    }

    /**
     * Busca y muestra las pistas disponibles que cumplen con el tipo y número de jugadores.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void buscarPistasLibres(Scanner scanner) {
        try {
            System.out.print("¿Es interior? (true/false): ");
            boolean interior = scanner.nextBoolean();
            System.out.print("Mínimo de jugadores: ");
            int minJugadores = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            List<Pista> pistas = pistaDAO.obtenerPistas();
            System.out.println("\n--- Pistas Libres ---");
            for (Pista pista : pistas) {
                if (pista.isDisponibilidad() && pista.isInterior() == interior && pista.getnMaximo() >= minJugadores) {
                    System.out.println(pista);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar pistas libres: " + e.getMessage());
        }
    }
}
