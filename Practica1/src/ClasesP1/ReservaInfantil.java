package ClasesP1;

import java.util.Date;

public class ReservaInfantil extends Reserva {
    private int numNinos;

    public ReservaInfantil() {
        super();
    }

    public ReservaInfantil(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numNinos) {
        super(usuarioId, fechaHora, duracion, pistaId, precio, descuento);
        this.numNinos = numNinos;
    }

    public int getNumNinos() { return numNinos; }
    public void setNumNinos(int numNinos) { this.numNinos = numNinos; }

    @Override
    public String toString() {
        return super.toString() + ", Número de niños: " + numNinos;
    }
}
