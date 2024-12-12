package business;

import business.pista_material.PistaMaterialDTO;
import data.dao.PistaMaterialDAO;

import java.sql.SQLException;
import java.util.List;

public class GestorPistaMaterial {
    private final PistaMaterialDAO pistaMaterialDAO = new PistaMaterialDAO();

    public void asociarMaterialConPista(PistaMaterialDTO pistaMaterial) throws SQLException {
        pistaMaterialDAO.insertarRelacion(pistaMaterial);
    }

    public List<PistaMaterialDTO> listarMaterialesDePista(int pistaId) throws SQLException {
        return pistaMaterialDAO.obtenerMaterialesPorPista(pistaId);
    }

    public void actualizarCantidadMaterialEnPista(int pistaId, int materialId, int nuevaCantidad) throws SQLException {
        pistaMaterialDAO.actualizarCantidad(pistaId, materialId, nuevaCantidad);
    }

    public void eliminarMaterialDePista(int pistaId, int materialId) throws SQLException {
        pistaMaterialDAO.eliminarRelacion(pistaId, materialId);
    }
}
