package business.reserva_bono;

public class ReservaBonoDTO {
    private int reservaId;
    private int bonoId;
    private int numeroSesion;

    public ReservaBonoDTO() {}

    public ReservaBonoDTO(int reservaId, int bonoId, int numeroSesion) {
        this.reservaId = reservaId;
        this.bonoId = bonoId;
        this.numeroSesion = numeroSesion;
    }

    // Getters y setters
    public int getReservaId() { return reservaId; }
    public void setReservaId(int reservaId) { this.reservaId = reservaId; }
    public int getBonoId() { return bonoId; }
    public void setBonoId(int bonoId) { this.bonoId = bonoId; }
    public int getNumeroSesion() { return numeroSesion; }
    public void setNumeroSesion(int numeroSesion) { this.numeroSesion = numeroSesion; }

    @Override
    public String toString() {
        return "ReservaBonoDTO{" +
                "reservaId=" + reservaId +
                ", bonoId=" + bonoId +
                ", numeroSesion=" + numeroSesion +
                '}';
    }
}
