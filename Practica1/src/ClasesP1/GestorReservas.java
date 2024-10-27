package ClasesP1;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Instant;
import java.time.ZoneId;

public class GestorReservas {
    private List<Reserva> reservas;
    private List<Bono> bonos;
    private GestorUsuarios gestorUsuarios;
    private GestorPistas gestorPistas;

    public GestorReservas(GestorUsuarios gestorUsuarios, GestorPistas gestorPistas) {
        this.reservas = new ArrayList<>();
        this.bonos = new ArrayList<>();
        this.gestorUsuarios = gestorUsuarios;
        this.gestorPistas = gestorPistas;
    }

    public boolean hacerReservaIndividual(String correoUsuario, LocalDateTime fechaHora, int duracion, String nombrePista, int numJugadores, TipoReserva tipoReserva) {
        if (!puedeRealizarReserva(fechaHora)) {
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

        Reserva nuevaReserva;
        switch (tipoReserva) {
            case INFANTIL:
                nuevaReserva = new ReservaInfantil();
                break;
            case FAMILIAR:
                nuevaReserva = new ReservaFamiliar();
                break;
            case ADULTOS:
                nuevaReserva = new ReservaAdultos();
                break;
            default:
                throw new IllegalArgumentException("Tipo de reserva no reconocido.");
        }
        reservas.add(nuevaReserva);

        System.out.println("Reserva realizada con éxito. Precio: " + precio + " euros.");
        return true;
    }

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

    public int contarReservasFuturas() {
        Temporal ahora = LocalDateTime.now();
        return (int) reservas.stream()
                             .filter(r -> r.getFechaHora().after((Date) ahora))
                             .count();
    }

    @SuppressWarnings("deprecation")
	public List<Reserva> consultarReservasPorDiaYPista(LocalDate fecha, String nombrePista) {
        return reservas.stream()
                       .filter(r -> r.getFechaHora().toGMTString().equals(fecha) && 
                                    r.getPista().getNombre().equals(nombrePista))
                       .collect(Collectors.toList());
    }

    private boolean puedeRealizarReserva(Date date) {
        Date fechaLimite = Date.from(LocalDateTime.now().plusHours(24).atZone(ZoneId.systemDefault()).toInstant());
        return date.after(fechaLimite);
    }



    private double calcularPrecioReserva(int duracion) {
        switch (duracion) {
            case 60: return 20;
            case 90: return 30;
            case 120: return 40;
            default: throw new IllegalArgumentException("Duración no válida");
        }
    }

    private boolean esPistaAdecuada(Pista pista, TipoReserva tipoReserva, int numJugadores) {
        return pista.getTamanio() == tipoReserva.getTamanoPista() && 
               pista.getNumMaxJugadores() >= numJugadores;
    }

    private Bono buscarBonoActivo(String correoUsuario, TipoReserva tipoReserva) {
        return bonos.stream()
                    .filter(b -> b.getUsuario().getCorreoElectronico().equals(correoUsuario) &&
                                 b.getTipoReserva() == tipoReserva &&
                                 b.getSesionesRestantes() > 0 &&
                                 b.getFechaCaducidad().isAfter(LocalDate.now()))
                    .findFirst()
                    .orElse(null);
    }

    private Bono crearNuevoBono(String correoUsuario, TipoReserva tipoReserva) {
        Jugador usuario = gestorUsuarios.buscarUsuario(correoUsuario);
        Bono nuevoBono = new Bono(usuario, tipoReserva);
        bonos.add(nuevoBono);
        return nuevoBono;
    }

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
