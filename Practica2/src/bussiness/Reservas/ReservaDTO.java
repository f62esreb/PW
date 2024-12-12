package business.reservas;

import java.util.Date;

public class ReservaDTO {
    private int id;
    private int usuarioId;
    private int pistaId;
    private Date fechaHora;
    private int duracionMinutos;
    private double precio;
    private double descuento;
    private String tipoReserva;
    private int numeroNinos;
    private int numeroAdultos;
    private int numeroAdultosParticipantes;
    private int numeroNinosParticipantes;
    private boolean esBono;
    private Integer idBono;
    private Integer numeroSesion;

    public ReservaDTO() {}

    public ReservaDTO(int id, int usuarioId, int pistaId, Date fechaHora, int duracionMinutos, double precio, 
                      double descuento, String tipoReserva, int numeroNinos, int numeroAdultos, 
                      int numeroAdultosParticipantes, int numeroNinosParticipantes, boolean esBono, 
                      Integer idBono, Integer numeroSesion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.pistaId = pistaId;
        this.fechaHora = fechaHora;
        this.duracionMinutos = duracionMinutos;
        this.precio = precio;
        this.descuento = descuento;
        this.tipoReserva = tipoReserva;
        this.numeroNinos = numeroNinos;
        this.numeroAdultos = numeroAdultos;
        this.numeroAdultosParticipantes = numeroAdultosParticipantes;
        this.numeroNinosParticipantes = numeroNinosParticipantes;
        this.esBono = esBono;
        this.idBono = idBono;
        this.numeroSesion = numeroSesion;
    }

    // Getters y setters
    // ... (omitidos por brevedad, disponibles bajo demanda)

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", pistaId=" + pistaId +
                ", fechaHora=" + fechaHora +
                ", duracionMinutos=" + duracionMinutos +
                ", precio=" + precio +
                ", descuento=" + descuento +
                ", tipoReserva='" + tipoReserva + '\'' +
                ", numeroNinos=" + numeroNinos +
                ", numeroAdultos=" + numeroAdultos +
                ", numeroAdultosParticipantes=" + numeroAdultosParticipantes +
                ", numeroNinosParticipantes=" + numeroNinosParticipantes +
                ", esBono=" + esBono +
                ", idBono=" + idBono +
                ", numeroSesion=" + numeroSesion +
                '}';
    }
}
