package display.main;

import business.GestorPistaMaterial;
import business.pista_material.PistaMaterialDTO;

public class MainPistaMaterial {
    public static void main(String[] args) {
        GestorPistaMaterial gestor = new GestorPistaMaterial();

        try {
            // Asociar material a pista
            PistaMaterialDTO nuevaRelacion = new PistaMaterialDTO(1, 2, 10); // PistaId: 1, MaterialId: 2, Cantidad: 10
            gestor.asociarMaterialConPista(nuevaRelacion);

            // Listar materiales asociados a una pista
            System.out.println("Materiales asociados a la pista 1:");
            gestor.listarMaterialesDePista(1).forEach(System.out::println);

            // Actualizar cantidad de un material en una pista
            gestor.actualizarCantidadMaterialEnPista(1, 2, 15); // Actualizar Cantidad a 15
            System.out.println("Cantidad actualizada:");
            gestor.listarMaterialesDePista(1).forEach(System.out::println);

            // Eliminar material de una pista
            gestor.eliminarMaterialDePista(1, 2);
            System.out.println("Material eliminado:");
            gestor.listarMaterialesDePista(1).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
