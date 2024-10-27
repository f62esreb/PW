package ClasesP1;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Clase que gestiona las reservas de pistas.
 */
public class GestorReservas {
    private List<Reserva> reservas;
    private List<Bono> bonos;
    private GestorUsuarios gestorUsuarios;
    private GestorDePistas gestorPistas;
    private static final String FILE_RESERVAS = "reservas.txt";

    /**
     * Constructor que inicializa las listas de reservas y bonos, y carga los datos desde el archivo.
     */
    public GestorReservas(GestorUsuarios gestorUsuarios, GestorDePistas gestorPistas) {
        this.reservas = new ArrayList<>();
        this.bonos = new ArrayList<>();
        this.gestorUsuarios = gestorUsuarios;
        this.gestorPistas = gestorPistas;
        cargarReservas();
    }

    /**
     * Menú interactivo para gestionar reservas.
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
                case 1:
                    hacerReservaIndividualMenu(scanner);
                    break;
                case 2:
                    hacerReservaBonoMenu(scanner);
                    break;
                case 3:
                    modificarReservaMenu(scanner);
                    break;
                case 4:
                    cancelarReservaMenu(scanner);
                    break;
                case 5:
                    consultarReservasFuturas();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    /**
     * Método para realizar una reserva individual, solicitando los datos al usuario.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void hacerReservaIndividualMenu(Scanner scanner) {
        System.out.print("Correo del usuario: ");
        String correo = scanner.nextLine();
        System.out.print("Nombre de la pista: ");
        String nombrePista = scanner.nextLine();
        System.out.print("Fecha y hora (yyyy-MM-ddTHH:mm): ");
        String fechaHoraStr = scanner.nextLine();
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);
        System.out.print("Duración (minutos): ");
        int duracion = scanner.nextInt();
        System.out.print("Número de jugadores: ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo de reserva (INFANTIL, FAMILIAR, ADULTOS): ");
        TipoReserva tipoReserva = TipoReserva.valueOf(scanner.nextLine().toUpperCase());

        boolean resultado = hacerReservaIndividual(correo, fechaHora, duracion, nombrePista, numJugadores, tipoReserva);
        System.out.println(resultado ? "Reserva realizada con éxito." : "No se pudo hacer la reserva.");
    }

    /**
     * Método para realizar una reserva utilizando un bono, solicitando los datos al usuario.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void hacerReservaBonoMenu(Scanner scanner) {
        System.out.print("Correo del usuario: ");
        String correo = scanner.nextLine();
        System.out.print("Nombre de la pista: ");
        String nombrePista = scanner.nextLine();
        System.out.print("Fecha y hora (yyyy-MM-ddTHH:mm): ");
        String fechaHoraStr = scanner.nextLine();
        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);
        System.out.print("Duración (minutos): ");
        int duracion = scanner.nextInt();
        System.out.print("Número de jugadores: ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Tipo de reserva (INFANTIL, FAMILIAR, ADULTOS): ");
        TipoReserva tipoReserva = TipoReserva.valueOf(scanner.nextLine().toUpperCase());

        boolean resultado = hacerReservaBono(correo, fechaHora, duracion, nombrePista, numJugadores, tipoReserva);
        System.out.println(resultado ? "Reserva realizada con éxito." : "No se pudo hacer la reserva.");
    }

    /**
     * Método para modificar una reserva existente, solicitando los datos al usuario.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void modificarReservaMenu(Scanner scanner) {
        System.out.print("ID de la reserva a modificar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nueva fecha y hora (yyyy-MM-ddTHH:mm): ");
        String nuevaFechaHoraStr = scanner.nextLine();
        LocalDateTime nuevaFechaHora = LocalDateTime.parse(nuevaFechaHoraStr);

        boolean resultado = modificarReserva(idReserva, Date.from(nuevaFechaHora.atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println(resultado ? "Reserva modificada con éxito." : "No se pudo modificar la reserva.");
    }

    /**
     * Método para cancelar una reserva existente, solicitando el ID al usuario.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void cancelarReservaMenu(Scanner scanner) {
        System.out.print("ID de la reserva a cancelar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        boolean resultado = cancelarReserva(idReserva);
        System.out.println(resultado ? "Reserva cancelada con éxito." : "No se pudo cancelar la reserva.");
    }

    /**
     * Método para consultar y listar las reservas futuras.
     */
    private void consultarReservasFuturas() {
        List<Reserva> futuras = reservas.stream()
                                        .filter(r -> r.getFechaHora().after(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())))
                                        .collect(Collectors.toList());
        if (futuras.isEmpty()) {
            System.out.println("No hay reservas futuras.");
        } else {
            futuras.forEach(reserva -> System.out.println(reserva));
        }
    }

    /**
     * Método para realizar una reserva individual.
     */
    public boolean hacerReservaIndividual(String correoUsuario, LocalDateTime fechaHora, int duracion, String nombrePista, int numJugadores, TipoReserva tipoReserva) {
        if (!puedeRealizarReserva(Date.from(fechaHora.atZone(ZoneId.systemDefault()).toInstant()))) {
            System.out.println("Las reservas deben realizarse con al menos 24 horas de antelación.");
            return false;
        }

        Jugador usuario = gestorUsuarios.buscarUsuario(correoUsuario);
        if (usuario == null) {
            System.out.println("Usuario no registrado.");
            return false;
        }

        Pista pista = gestorPistas.buscarPista(nombrePista);
        if (pista == null || !pista.getPistaDisponible() || !esPistaAdecuada(pista, tipoReserva, numJugadores)) {
            System.out.println("Pista no disponible o no adecuada para la reserva.");
            return false;
        }

        double precio = calcularPrecioReserva(duracion);
        if (usuario.calcularAntiguedad() > 2) {
            precio *= 0.9; // 10% de descuento por antigüedad
        }

        Reserva nuevaReserva = new Reserva(usuario, fechaHora, duracion, pista, precio, tipoReserva, numJugadores);
        reservas.add(nuevaReserva);

        System.out.println("Reserva realizada con éxito. Precio: " + precio + " euros.");
        return true;
    }

    /**
     * Método para realizar una reserva utilizando un bono.
     */
    public boolean hacerReservaBono(String correoUsuario, LocalDateTime fechaHora, int duracion, String nombrePista, int numJugadores, TipoReserva tipoReserva) {
        Bono bono = buscarBonoActivo(correoUsuario, tipoReserva);
        if (bono == null) {
            bono = crearNuevoBono(correoUsuario, tipoReserva);
        }

        if (bono.getSesionesRestantes() == 0) {
            System.out.println("El bono no tiene sesiones disponibles.");
            return false;
        }

        double precio = calcularPrecioReserva(duracion) * 0.95; // 5% de descuento por bono
        Reserva nuevaReserva = new Reserva(bono.getUsuario(), fechaHora, duracion, gestorPistas.buscarPista(nombrePista), precio, tipoReserva, numJugadores);
        
        bono.agregarReserva(nuevaReserva);
        reservas.add(nuevaReserva);

        System.out.println("Reserva de bono realizada con éxito. Sesiones restantes: " + bono.getSesionesRestantes());
        return true;
    }

    /**
     * Método para modificar una reserva.
     */
    public boolean modificarReserva(int idReserva, Date nuevaFechaHora) {
        Reserva reserva = buscarReserva(idReserva);
        if (reserva == null || !puedeRealizarReserva(reserva.getFechaHora())) {
            System.out.println("No se puede modificar la reserva.");
            return false;
        }

        reserva.setFechaHora(nuevaFechaHora);
        System.out.println("Reserva modificada con éxito.");
        return true;
    }

    /**
     * Método para cancelar una reserva.
     */
    public boolean cancelarReserva(int idReserva) {
        Reserva reserva = buscarReserva(idReserva);
        if (reserva == null || !puedeRealizarReserva(reserva.getFechaHora())) {
            System.out.println("No se puede cancelar la reserva.");
            return false;
        }

        reservas.remove(reserva);
        System.out.println("Reserva cancelada con éxito.");
        return true;
    }

    /**
     * Método para guardar las reservas actuales en un archivo de texto.
     */
    public void guardarReservas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_RESERVAS))) {
            for (Reserva reserva : reservas) {
                bw.write(reserva.toString()); // Convierte la reserva a un formato de texto adecuado
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo de reservas: " + e.getMessage());
        }
    }

    /**
     * Método para cargar las reservas desde un archivo de texto.
     */
    public void cargarReservas() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RESERVAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Convertir cada línea en una instancia de Reserva y añadir a la lista de reservas
                // Implementar el código para convertir desde el formato guardado a una reserva
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de reservas: " + e.getMessage());
        }
    }

    /**
     * Método para verificar si se puede realizar una reserva con al menos 24 horas de anticipación.
     */
    private boolean puedeRealizarReserva(Date fechaHora) {
        Date fechaLimite = Date.from(LocalDateTime.now().plusHours(24).atZone(ZoneId.systemDefault()).toInstant());
        return fechaHora.after(fechaLimite);
    }

    /**
     * Calcula el precio de la reserva en función de la duración.
     */
    private double calcularPrecioReserva(int duracion) {
        switch (duracion) {
            case 60: return 20;
            case 90: return 30;
            case 120: return 40;
            default: throw new IllegalArgumentException("Duración no válida");
        }
    }

    /**
     * Verifica si la pista es adecuada para el tipo de reserva y número de jugadores.
     */
    private boolean esPistaAdecuada(Pista pista, TipoReserva tipoReserva, int numJugadores) {
        return pista.getTam() == tipoReserva.getTamanoPista() && pista.getNMax() >= numJugadores;
    }

    /**
     * Busca un bono activo de un usuario para un tipo de reserva específico.
     */
    private Bono buscarBonoActivo(String correoUsuario, TipoReserva tipoReserva) {
        return bonos.stream()
                    .filter(b -> b.getUsuario().getCorreo().equals(correoUsuario) &&
                                 b.getTipoReserva() == tipoReserva &&
                                 b.getSesionesRestantes() > 0 &&
                                 b.getFechaCaducidad().isAfter(LocalDate.now()))
                    .findFirst()
                    .orElse(null);
    }

    /**
     * Crea un nuevo bono para un usuario.
     */
    private Bono crearNuevoBono(String correoUsuario, TipoReserva tipoReserva) {
        Jugador usuario = gestorUsuarios.buscarUsuario(correoUsuario);
        Bono nuevoBono = new Bono(usuario, tipoReserva);
        bonos.add(nuevoBono);
        return nuevoBono;
    }

    /**
     * Busca una reserva por su ID.
     */
    private Reserva buscarReserva(int idReserva) {
        return reservas.stream()
                       .filter(r -> r.getId() == idReserva)
                       .findFirst()
                       .orElse(null);
    }
}


class Bono {
    private Jugador usuario;
    private TipoReserva tipoReserva;
    private int sesionesRestantes;
    private LocalDate fechaCaducidad;
    private List<Reserva> reservas;

    public Bono(Jugador usuario, TipoReserva tipoReserva) {
        this.usuario = usuario;
        this.tipoReserva = tipoReserva;
        this.sesionesRestantes = 5;
        this.fechaCaducidad = LocalDate.now().plusYears(1);
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        if (sesionesRestantes > 0) {
            reservas.add(reserva);
            sesionesRestantes--;
        }
    }

    // Getters y setters
    public Jugador getUsuario() { return usuario; }
    public TipoReserva getTipoReserva() { return tipoReserva; }
    public int getSesionesRestantes() { return sesionesRestantes; }
    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
}

enum TipoReserva {
    INFANTIL(TamanoPista.MINIBASKET),
    FAMILIAR(TamanoPista.TRES_VS_TRES),
    ADULTOS(TamanoPista.ADULTOS);

    private TamanoPista tamanoPista;

    TipoReserva(TamanoPista tamanoPista) {
        this.tamanoPista = tamanoPista;
    }

    public TamanoPista getTamanoPista() {
        return tamanoPista;
    }
}

enum TamanoPista {
    MINIBASKET, ADULTOS, TRES_VS_TRES
}
