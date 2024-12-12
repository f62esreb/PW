package display.main;

import business.GestorMateriales;
import business.materiales.MaterialDTO;

public class MainMaterial {
    public static void main(String[] args) {
        GestorMateriales gestor = new GestorMateriales();

        try {
            MaterialDTO nuevoMaterial = new MaterialDTO(0, "Raqueta", true, "Disponible");
            gestor.registrarMaterial(nuevoMaterial);

            System.out.println("Materiales registrados:");
            gestor.listarMateriales().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
