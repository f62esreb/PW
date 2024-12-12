package business;

import business.pistas.PistaDTO;
import data.dao.PistaDAO;

import java.sql.SQLException;
import java.util.List;

public class GestorPistas {
    private final PistaDAO pistaDAO = new PistaDAO();

    public void registrarPista(PistaDTO pista) throws SQLException {
        pistaDAO.insertarPista(pista);
    }

    public List<PistaDTO> listarPistas() throws SQLException {
        return pistaDAO.listarPistas();
    }

    public PistaDTO obtenerPistaPorId(int id) throws SQLException {
        return pistaDAO.obtenerPistaPorId(id);
    }
}
