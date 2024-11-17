package ClasesP1;

import java.util.Date;

public class ReservaFamiliar extends Reserva {
    private int numAdultos;
    private int numNinos;

    // Constructor sin parámetros
    public ReservaFamiliar() {
        super(); // Llama al constructor sin parámetros de Reserva
    }

    // Constructor con parámetros
    public ReservaFamiliar(int id, String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numAdultos, int numNinos) {
        super(id, usuarioId, fechaHora, duracion, pistaId, precio, descuento);
        this.numAdultos = numAdultos;
        this.numNinos = numNinos;
    }

    public int getNumAdultos() { return numAdultos; }
    public void setNumAdultos(int numAdultos) { this.numAdultos = numAdultos; }
    
    public int getNumNinos() { return numNinos; }
    public void setNumNinos(int numNinos) { this.numNinos = numNinos; }

    @Override
    public String toString() {
        return super.toString() + ", Número de adultos: " + numAdultos + ", Número de niños: " + numNinos;
    }
}
