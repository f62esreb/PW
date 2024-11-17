package ClasesP1;

import java.util.Date;

public abstract class ReservaFactory {
    public abstract ReservaAdultos crearReservaAdultos(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numParticipantes);

    public abstract ReservaInfantil crearReservaInfantil(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numNinos);

    public abstract ReservaFamiliar crearReservaFamiliar(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento, int numAdultos, int numNinos);
}
