package test;

import data.dao.JugadorDAO;
import dto.Jugador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJugadorDAO {

    private JugadorDAO jugadorDAO;

    @BeforeEach
    public void setUp() {
        jugadorDAO = new JugadorDAO();
    }

    @AfterEach
    public void tearDown() {
        jugadorDAO = null;
    }

    @Test
    public void testInsertarJugador() throws SQLException {
        Jugador jugador = new Jugador(12345678, "Juan Pérez", java.sql.Date.valueOf("1990-01-01"),
                java.sql.Date.valueOf("2024-11-17"), "juanperez@example.com");
        jugadorDAO.insertarJugador(jugador);

        // Verificamos que el jugador ha sido insertado correctamente
        Jugador jugadorRecuperado = jugadorDAO.obtenerJugadorPorDni(12345678);
        assertNotNull(jugadorRecuperado);
        assertEquals(jugador.getDni(), jugadorRecuperado.getDni());
        assertEquals(jugador.getNombreCompleto(), jugadorRecuperado.getNombreCompleto());
    }

    @Test
    public void testObtenerJugadores() throws SQLException {
        List<Jugador> jugadores = jugadorDAO.obtenerJugadores();
        assertTrue(jugadores.size() > 0, "La lista de jugadores no debería estar vacía.");
    }

    @Test
    public void testActualizarJugador() throws SQLException {
        Jugador jugador = new Jugador(12345678, "Juan Pérez", java.sql.Date.valueOf("1990-01-01"),
                java.sql.Date.valueOf("2024-11-17"), "juanperez@example.com");
        jugadorDAO.insertarJugador(jugador);

        jugador.setCorreo("juanperez@nuevoemail.com");
        jugadorDAO.actualizarJugador(jugador);

        Jugador jugadorActualizado = jugadorDAO.obtenerJugadorPorDni(12345678);
        assertEquals("juanperez@nuevoemail.com", jugadorActualizado.getCorreo());
    }

    @Test
    public void testEliminarJugador() throws SQLException {
        Jugador jugador = new Jugador(12345678, "Juan Pérez", java.sql.Date.valueOf("1990-01-01"),
                java.sql.Date.valueOf("2024-11-17"), "juanperez@example.com");
        jugadorDAO.insertarJugador(jugador);

        jugadorDAO.eliminarJugador(12345678);
        Jugador jugadorEliminado = jugadorDAO.obtenerJugadorPorDni(12345678);
        assertNull(jugadorEliminado);
    }
}
