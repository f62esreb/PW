package dto;

public class ReservaBono {
    private int idReserva;
    private int idBono;
    private int numSesion;

    // Constructor
    public ReservaBono(int idReserva, int idBono, int numSesion) {
        this.idReserva = idReserva;
        this.idBono = idBono;
        this.numSesion = numSesion;
    }

    // Getters y Setters
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdBono() {
        return idBono;
    }

    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    public int getNumSesion() {
        return numSesion;
    }

    public void setNumSesion(int numSesion) {
        this.numSesion = numSesion;
    }

    @Override
    public String toString() {
        return "ReservaBono [idReserva=" + idReserva + ", idBono=" + idBono + ", numSesion=" + numSesion + "]";
    }
}
