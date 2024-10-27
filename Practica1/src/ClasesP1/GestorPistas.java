package ClasesP1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona las pistas y materiales necesarios para dar servicio.
 */
public class GestorDePistas {

    // Atributos.
    private ArrayList<Pista> pistas;
    private ArrayList<Material> materiales;
    private static final String FILE_PISTAS = "pistas.txt";
    private static final String FILE_MATERIALES = "materiales.txt";

    /**
     * Constructor vacío que inicializa las listas de pistas y materiales y carga los datos desde los archivos.
     */
    public GestorDePistas() {
        this.pistas = new ArrayList<>();
        this.materiales = new ArrayList<>();
        cargarPistas();
        cargarMateriales();
    }

    /**
     * Menú interactivo para gestionar pistas.
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
                case 1:
                    crearNuevaPista(scanner);
                    break;
                case 2:
                    crearNuevoMaterial(scanner);
                    break;
                case 3:
                    asociarMaterialAPista(scanner);
                    break;
                case 4:
                    listarPistasNoDisponibles();
                    break;
                case 5:
                    buscarPistasLibres(scanner);
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
     * Método para crear una nueva pista y añadirla a la lista de pistas.
     * Solicita los datos al usuario y crea la pista.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void crearNuevaPista(Scanner scanner) {
        System.out.print("Nombre de la pista: ");
        String nombre = scanner.nextLine();
        System.out.print("¿Es interior? (true/false): ");
        boolean esInterior = scanner.nextBoolean();
        System.out.print("¿Está disponible? (true/false): ");
        boolean disponible = scanner.nextBoolean();
        System.out.print("Tamaño (1=Minibasket, 2=Adultos, 3=3vs3): ");
        int tam = scanner.nextInt();
        System.out.print("Máximo jugadores: ");
        int maxJugadores = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Pista.tamPista tamano = (tam == 1) ? Pista.tamPista.MINIBASKET :
                                (tam == 2) ? Pista.tamPista.ADULTOS : Pista.tamPista._3VS3;

        Pista nuevaPista = new Pista(nombre, disponible, esInterior, tamano, maxJugadores);
        crearPista(nuevaPista);
        System.out.println("Pista creada con éxito.");
    }

    /**
     * Método para crear un nuevo material y añadirlo a la lista de materiales.
     * Solicita los datos al usuario y crea el material.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void crearNuevoMaterial(Scanner scanner) {
        System.out.print("ID del material: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Tipo de material (PELOTAS, CANASTAS, CONOS): ");
        Material.tipoMaterial tipo = Material.tipoMaterial.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("¿Es para interior? (true/false): ");
        boolean usoInterior = scanner.nextBoolean();
        System.out.print("Estado del material (DISPONIBLE, RESERVADO, MAL_ESTADO): ");
        Material.estadoMaterial estado = Material.estadoMaterial.valueOf(scanner.next().toUpperCase());

        Material material = new Material(id, tipo, usoInterior, estado);
        crearMaterial(material);
        System.out.println("Material creado con éxito.");
    }

    /**
     * Método para asociar un material a una pista disponible.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void asociarMaterialAPista(Scanner scanner) {
        System.out.print("Nombre de la pista: ");
        String nombrePista = scanner.nextLine();
        System.out.print("ID del material: ");
        int idMaterial = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (asociarMaterialAPista(nombrePista, idMaterial)) {
            System.out.println("Material asociado con éxito.");
        } else {
            System.out.println("No se pudo asociar el material.");
        }
    }

    /**
     * Lista las pistas que no están disponibles.
     */
    private void listarPistasNoDisponibles() {
        System.out.println("\n--- Pistas No Disponibles ---");
        for (Pista pista : listarPistasNoDisponibles()) {
            System.out.println(pista);
        }
    }

    /**
     * Busca y muestra las pistas disponibles que cumplen con el tipo y número de jugadores.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void buscarPistasLibres(Scanner scanner) {
        System.out.print("¿Es interior? (true/false): ");
        boolean tipoPista = scanner.nextBoolean();
        System.out.print("Mínimo jugadores: ");
        int minJugadores = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.println("\n--- Pistas Libres ---");
        for (Pista pista : buscarPistasLibres(tipoPista, minJugadores)) {
            System.out.println(pista);
        }
    }

    /**
     * Método para guardar las pistas actuales en un archivo de texto.
     */
    public void guardarPistas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PISTAS))) {
            for (Pista pista : pistas) {
                String datos = pista.getNombre() + "," + pista.getPistaDisponible() + "," +
                               pista.getTipoPista() + "," + pista.getTam() + "," + pista.getNMax();
                bw.write(datos);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de pistas: " + e.getMessage());
        }
    }

    /**
     * Método para cargar las pistas desde un archivo de texto.
     */
    public void cargarPistas() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PISTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                boolean disponible = Boolean.parseBoolean(datos[1]);
                boolean esInterior = Boolean.parseBoolean(datos[2]);
                Pista.tamPista tam = Pista.tamPista.valueOf(datos[3]);
                int maxJugadores = Integer.parseInt(datos[4]);
                pistas.add(new Pista(nombre, disponible, esInterior, tam, maxJugadores));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de pistas: " + e.getMessage());
        }
    }

    /**
     * Método para guardar los materiales actuales en un archivo de texto.
     */
    public void guardarMateriales() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_MATERIALES))) {
            for (Material material : materiales) {
                String datos = material.getId() + "," + material.getTipo() + "," + material.getUsoInterior() + "," + material.getEstado();
                bw.write(datos);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de materiales: " + e.getMessage());
        }
    }

    /**
     * Método para cargar los materiales desde un archivo de texto.
     */
    public void cargarMateriales() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_MATERIALES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                Material.tipoMaterial tipo = Material.tipoMaterial.valueOf(datos[1]);
                boolean usoInterior = Boolean.parseBoolean(datos[2]);
                Material.estadoMaterial estado = Material.estadoMaterial.valueOf(datos[3]);
                materiales.add(new Material(id, tipo, usoInterior, estado));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de materiales: " + e.getMessage());
        }
    }

}
