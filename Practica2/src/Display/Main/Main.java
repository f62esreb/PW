package display.main;

import business.GestorUsuarios;
import business.jugadores.JugadorDTO;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        GestorUsuarios gestor = new GestorUsuarios();

        try {
            JugadorDTO nuevoJugador = new JugadorDTO(0, "Juan Pérez", new Date(90, 5, 24), new Date(), "juan.perez@example.com");
            gestor.registrarJugador(nuevoJugador);

            System.out.println("Jugadores registrados:");
            gestor.listarUsuarios().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

