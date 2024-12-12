package display.main;

import business.GestorReservaBono;
import business.reserva_bono.ReservaBonoDTO;

public class MainReservaBono {
    public static void main(String[] args) {
        GestorReservaBono gestor = new GestorReservaBono();

        try {
            // Asociar bono a reserva
            ReservaBonoDTO nuevaRelacion = new ReservaBonoDTO(1, 1, 5); // ReservaId: 1, BonoId: 1, NumeroSesion: 5
            gestor.asociarBonoAReserva(nuevaRelacion);

            // Listar bonos asociados a una reserva
            System.out.println("Bonos asociados a la reserva 1:");
            gestor.listarBonosDeReserva(1).forEach(System.out::println);

            // Eliminar bono de una reserva
            gestor.eliminarBonoDeReserva(1, 1);
            System.out.println("Bonos restantes asociados a la reserva 1:");
            gestor.listarBonosDeReserva(1).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
