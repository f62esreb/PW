package business;

import business.materiales.MaterialDTO;
import data.dao.MaterialDAO;

import java.sql.SQLException;
import java.util.List;

public class GestorMateriales {
    private final MaterialDAO materialDAO = new MaterialDAO();

    public void registrarMaterial(MaterialDTO material) throws SQLException {
        if (materialDAO.obtenerMaterialPorId(material.getId()) != null) {
            throw new IllegalArgumentException("El material ya está registrado.");
        }
        materialDAO.insertarMaterial(material);
    }

    public List<MaterialDTO> listarMateriales() throws SQLException {
        return materialDAO.listarMateriales();
    }
}
