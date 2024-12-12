package business;

import business.reserva_bono.ReservaBonoDTO;
import data.dao.ReservaBonoDAO;

import java.sql.SQLException;
import java.util.List;

public class GestorReservaBono {
    private final ReservaBonoDAO reservaBonoDAO = new ReservaBonoDAO();

    public void asociarBonoAReserva(ReservaBonoDTO reservaBono) throws SQLException {
        reservaBonoDAO.asociarReservaConBono(reservaBono);
    }

    public List<ReservaBonoDTO> listarBonosDeReserva(int reservaId) throws SQLException {
        return reservaBonoDAO.obtenerBonosPorReserva(reservaId);
    }

    public void eliminarBonoDeReserva(int reservaId, int bonoId) throws SQLException {
        reservaBonoDAO.eliminarRelacion(reservaId, bonoId);
    }
}
