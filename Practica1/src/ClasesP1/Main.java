package ClasesP1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Instancia de los gestores
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorDePistas gestorPistas = new GestorDePistas();
        GestorReservas gestorReservas = new GestorReservas(gestorUsuarios, gestorPistas);

        // Cargar datos al inicio
        gestorUsuarios.cargarUsuarios();
        gestorPistas.cargarPistas();
        gestorPistas.cargarMateriales();
        gestorReservas.cargarReservas();

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        
        while (opcion != 0) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de usuarios");
            System.out.println("2. Gestión de pistas");
            System.out.println("3. Gestión de reservas");
            System.out.println("0. Salir y guardar cambios");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    gestorUsuarios.menuGestionUsuarios(scanner);
                    break;
                case 2:
                    gestorPistas.menuGestionPistas(scanner);
                    break;
                case 3:
                    gestorReservas.menuGestionReservas(scanner);
                    break;
                case 0:
                    System.out.println("Guardando cambios y saliendo...");
                    gestorUsuarios.guardarUsuarios();
                    gestorPistas.guardarPistas();
                    gestorPistas.guardarMateriales();
                    gestorReservas.guardarReservas();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }
}
