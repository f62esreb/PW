package business;

import business.jugadores.JugadorDTO;
import data.dao.JugadorDAO;

import java.sql.SQLException;
import java.util.List;

public class GestorUsuarios {
    private final JugadorDAO jugadorDAO = new JugadorDAO();

    public void registrarJugador(JugadorDTO jugador) throws SQLException {
        if (jugadorDAO.obtenerJugadorPorId(jugador.getId()) != null) {
            throw new IllegalArgumentException("El jugador ya está registrado.");
        }
        jugadorDAO.insertarJugador(jugador);
    }

    public List<JugadorDTO> listarUsuarios() throws SQLException {
        return jugadorDAO.listarJugadores();
    }
}

