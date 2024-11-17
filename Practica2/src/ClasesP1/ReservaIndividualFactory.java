package ClasesP1;

import java.util.Date;

public class ReservaIndividualFactory extends ReservaFactory {

    @Override
    public ReservaAdultos crearReservaAdultos(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numParticipantes) {
        return new ReservaAdultos(usuarioId, fechaHora, duracion, pistaId, precio, descuento, numParticipantes);
    }

    @Override
    public ReservaInfantil crearReservaInfantil(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numNinos) {
        return new ReservaInfantil(usuarioId, fechaHora, duracion, pistaId, precio, descuento, numNinos);
    }

    @Override
    public ReservaFamiliar crearReservaFamiliar(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numAdultos, int numNinos) {
        return new ReservaFamiliar(-1, usuarioId, fechaHora, duracion, pistaId, precio, descuento, numAdultos, numNinos);
    }
}
