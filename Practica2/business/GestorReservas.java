package business;

import data.dao.BonoDAO;
import data.dao.ReservasDAO;
import Bono;
import Jugador;
import Pista;
import Reserva;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona las reservas de pistas utilizando DAOs y DTOs.
 */
public class GestorReservas {
    private final ReservasDAO reservasDAO;
    private final BonoDAO bonoDAO;
    private final GestorUsuarios gestorUsuarios;
    private final GestorDePistas gestorPistas;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    /**
     * Constructor que inicializa los DAOs y gestores relacionados.
     */
    public GestorReservas(GestorUsuarios gestorUsuarios, GestorDePistas gestorPistas) {
        this.reservasDAO = new ReservasDAO();
        this.bonoDAO = new BonoDAO();
        this.gestorUsuarios = gestorUsuarios;
        this.gestorPistas = gestorPistas;
    }

    /**
     * Menú interactivo para gestionar reservas.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    public void menuGestionReservas(Scanner scanner) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Menú de Gestión de Reservas ---");
            System.out.println("1. Hacer una reserva individual");
            System.out.println("2. Hacer una reserva con bono");
            System.out.println("3. Modificar reserva");
            System.out.println("4. Cancelar reserva");
            System.out.println("5. Consultar reservas futuras");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> hacerReservaIndividualMenu(scanner);
                case 2 -> hacerReservaBonoMenu(scanner);
                case 3 -> modificarReservaMenu(scanner);
                case 4 -> cancelarReservaMenu(scanner);
                case 5 -> consultarReservasFuturas();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Realiza una reserva individual.
     */
    private void hacerReservaIndividualMenu(Scanner scanner) {
        try {
            System.out.print("Correo del usuario: ");
            String correo = scanner.nextLine();
            System.out.print("Nombre de la pista: ");
            String nombrePista = scanner.nextLine();
            System.out.print("Fecha y hora (dd-MM-yyyy HH:mm): ");
            Date fechaHora = DATE_FORMAT.parse(scanner.nextLine());
            System.out.print("Duración (minutos): ");
            int duracion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            Pista pista = gestorPistas.buscarPista(nombrePista);
            if (pista == null || !pista.isDisponibilidad()) {
                System.out.println("Pista no disponible.");
                return;
            }

            Jugador usuario = gestorUsuarios.buscarUsuario(correo);
            if (usuario == null) {
                System.out.println("Usuario no registrado.");
                return;
            }

            Reserva reserva = new Reserva(0, usuario.getCorreo(), fechaHora, duracion, nombrePista, 20.0f, 0.0f);
            reservasDAO.insertarReserva(reserva);
            System.out.println("Reserva realizada con éxito.");
        } catch (ParseException e) {
            System.out.println("Error en el formato de la fecha. Use dd-MM-yyyy HH:mm.");
        } catch (SQLException e) {
            System.err.println("Error al realizar la reserva: " + e.getMessage());
        }
    }

    /**
     * Realiza una reserva utilizando un bono.
     */
    private void hacerReservaBonoMenu(Scanner scanner) {
        try {
            System.out.print("Correo del usuario: ");
            String correo = scanner.nextLine();
            System.out.print("Nombre de la pista: ");
            String nombrePista = scanner.nextLine();
            System.out.print("Fecha y hora (dd-MM-yyyy HH:mm): ");
            Date fechaHora = DATE_FORMAT.parse(scanner.nextLine());
            System.out.print("Duración (minutos): ");
            int duracion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            Bono bono = bonoDAO.buscarBonoActivo(correo);
            if (bono == null || bono.getSesionesRestantes() <= 0) {
                System.out.println("No hay bono activo o no tiene sesiones disponibles.");
                return;
            }

            Pista pista = gestorPistas.buscarPista(nombrePista);
            if (pista == null || !pista.isDisponibilidad()) {
                System.out.println("Pista no disponible.");
                return;
            }

            Reserva reserva = new Reserva(0, correo, fechaHora, duracion, nombrePista, 15.0f, 0.0f);
            reservasDAO.insertarReserva(reserva);
            bono.setSesionesRestantes(bono.getSesionesRestantes() - 1);
            bonoDAO.actualizarBono(bono);

            System.out.println("Reserva realizada con éxito.");
        } catch (ParseException e) {
            System.out.println("Error en el formato de la fecha. Use dd-MM-yyyy HH:mm.");
        } catch (SQLException e) {
            System.err.println("Error al realizar la reserva: " + e.getMessage());
        }
    }

    /**
     * Modifica una reserva existente.
     */
    private void modificarReservaMenu(Scanner scanner) {
        try {
            System.out.print("ID de la reserva a modificar: ");
            int idReserva = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nueva fecha y hora (dd-MM-yyyy HH:mm): ");
            Date nuevaFechaHora = DATE_FORMAT.parse(scanner.nextLine());

            Reserva reserva = reservasDAO.buscarReservaPorId(idReserva);
            if (reserva == null) {
                System.out.println("Reserva no encontrada.");
                return;
            }

            reserva.setFechaHora(nuevaFechaHora);
            reservasDAO.actualizarReserva(reserva);
            System.out.println("Reserva modificada con éxito.");
        } catch (ParseException e) {
            System.out.println("Error en el formato de la fecha. Use dd-MM-yyyy HH:mm.");
        } catch (SQLException e) {
            System.err.println("Error al modificar la reserva: " + e.getMessage());
        }
    }

    /**
     * Cancela una reserva existente.
     */
    private void cancelarReservaMenu(Scanner scanner) {
        try {
            System.out.print("ID de la reserva a cancelar: ");
            int idReserva = scanner.nextInt();
            scanner.nextLine();

            reservasDAO.eliminarReserva(idReserva);
            System.out.println("Reserva cancelada con éxito.");
        } catch (SQLException e) {
            System.err.println("Error al cancelar la reserva: " + e.getMessage());
        }
    }

    /**
     * Consulta y muestra las reservas futuras.
     */
    private void consultarReservasFuturas() {
        try {
            List<Reserva> reservasFuturas = reservasDAO.obtenerReservasFuturas();
            if (reservasFuturas.isEmpty()) {
                System.out.println("No hay reservas futuras.");
            } else {
                reservasFuturas.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar las reservas futuras: " + e.getMessage());
        }
    }
}
