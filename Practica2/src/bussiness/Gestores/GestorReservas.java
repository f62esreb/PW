package business;

import business.reservas.ReservaDTO;
import data.dao.ReservaDAO;

import java.sql.SQLException;
import java.util.List;

public class GestorReservas {
    private final ReservaDAO reservaDAO = new ReservaDAO();

    public void registrarReserva(ReservaDTO reserva) throws SQLException {
        reservaDAO.insertarReserva(reserva);
    }

    public List<ReservaDTO> listarReservas() throws SQLException {
        return reservaDAO.listarReservas();
    }
}
