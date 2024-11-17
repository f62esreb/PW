package test;

import data.dao.MaterialDAO;
import dto.Material;

import java.util.List;

public class TestMaterialDAO {
    public static void main(String[] args) {
        MaterialDAO materialDAO = new MaterialDAO();

        try {
            // Insertar un nuevo material
            Material material = new Material();
            material.setId(1);
            material.setTipo("pelotas");
            material.setInterior(true);
            material.setEstado("disponible");
            materialDAO.insertarMaterial(material);
            System.out.println("Material insertado: " + material);

            // Obtener todos los materiales
            List<Material> materiales = materialDAO.obtenerMateriales();
            System.out.println("Materiales en la base de datos:");
            for (Material m : materiales) {
                System.out.println(m);
            }

            // Actualizar un material
            material.setEstado("reservado");
            materialDAO.actualizarMaterial(material);
            System.out.println("Material actualizado: " + material);

            // Eliminar un material
            materialDAO.eliminarMaterial(1);
            System.out.println("Material eliminado con ID 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
