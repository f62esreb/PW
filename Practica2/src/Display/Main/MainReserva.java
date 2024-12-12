package display.main;

import business.GestorReservas;
import business.reservas.ReservaDTO;

import java.util.Date;

public class MainReserva {
    public static void main(String[] args) {
        GestorReservas gestor = new GestorReservas();

        try {
            ReservaDTO nuevaReserva = new ReservaDTO(0, 1, 2, new Date(), 90, 50.0, 10.0, 
                                                     "Particular", 2, 4, 3, 1, false, null, null);
            gestor.registrarReserva(nuevaReserva);

            System.out.println("Reservas registradas:");
            gestor.listarReservas().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
