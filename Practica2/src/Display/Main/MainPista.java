package display.main;

import business.GestorPistas;
import business.pistas.PistaDTO;

public class MainPista {
    public static void main(String[] args) {
        GestorPistas gestor = new GestorPistas();

        try {
            PistaDTO nuevaPista = new PistaDTO(0, "Pista Central", "Disponible", true, 20.5, 4);
            gestor.registrarPista(nuevaPista);

            System.out.println("Pistas registradas:");
            gestor.listarPistas().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
