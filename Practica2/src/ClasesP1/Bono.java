package ClasesP1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
