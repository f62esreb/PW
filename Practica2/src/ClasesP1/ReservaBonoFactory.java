package ClasesP1;

import java.util.Date;

public class ReservaBonoFactory extends ReservaFactory {

    @Override
    public ReservaAdultos crearReservaAdultos(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numParticipantes) {
        float bonoDescuento = descuento + 5; // Descuento adicional
        return new ReservaAdultos(usuarioId, fechaHora, duracion, pistaId, precio, bonoDescuento, numParticipantes);
    }

    @Override
    public ReservaInfantil crearReservaInfantil(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numNinos) {
        float bonoDescuento = descuento + 3; // Descuento adicional
        return new ReservaInfantil(usuarioId, fechaHora, duracion, pistaId, precio, bonoDescuento, numNinos);
    }

    @Override
    public ReservaFamiliar crearReservaFamiliar(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numAdultos, int numNinos) {
        float bonoDescuento = descuento + 10; // Descuento adicional
        return new ReservaFamiliar(-1, usuarioId, fechaHora, duracion, pistaId, precio, bonoDescuento, numAdultos, numNinos);
    }
}
