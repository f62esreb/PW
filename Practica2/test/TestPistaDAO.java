package test;

import data.dao.PistaDAO;
import dto.Pista;

import java.util.List;

public class TestPistaDAO {
    public static void main(String[] args) {
        PistaDAO pistaDAO = new PistaDAO();

        try {
            // Insertar una nueva pista
            Pista pista = new Pista();
            pista.setId(1);
            pista.setDisponibilidad(true);
            pista.setInterior(true);
            pista.setTamaño("ADULTOS");
            pista.setnMaximo(10);
            pistaDAO.insertarPista(pista);
            System.out.println("Pista insertada: " + pista);

            // Obtener todas las pistas
            List<Pista> pistas = pistaDAO.obtenerPistas();
            System.out.println("Pistas en la base de datos:");
            for (Pista p : pistas) {
                System.out.println(p);
            }

            // Actualizar una pista
            pista.setTamaño("TRES_VS_TRES");
            pista.setnMaximo(6);
            pistaDAO.actualizarPista(pista);
            System.out.println("Pista actualizada: " + pista);

            // Eliminar una pista
            pistaDAO.eliminarPista(1);
            System.out.println("Pista eliminada con ID 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
