package business.pistas;

public class PistaDTO {
    private int id;
    private String nombre;
    private String estado;
    private boolean tipoInterior;
    private double tamano;
    private int numeroMaxJugadores;

    public PistaDTO() {}

    public PistaDTO(int id, String nombre, String estado, boolean tipoInterior, double tamano, int numeroMaxJugadores) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.tipoInterior = tipoInterior;
        this.tamano = tamano;
        this.numeroMaxJugadores = numeroMaxJugadores;
    }

    // Getters y setters
    // ... (omitidos por brevedad, disponibles bajo demanda)

    @Override
    public String toString() {
        return "PistaDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                ", tipoInterior=" + tipoInterior +
                ", tamano=" + tamano +
                ", numeroMaxJugadores=" + numeroMaxJugadores +
                '}';
    }
}
