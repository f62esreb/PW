package test;

import data.dao.ReservaBonoDAO;
import dto.ReservaBono;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestReservaBonoDAO {

    private ReservaBonoDAO reservaBonoDAO;

    @BeforeEach
    public void setUp() {
        reservaBonoDAO = new ReservaBonoDAO();
    }

    @AfterEach
    public void tearDown() {
        reservaBonoDAO = null;
    }

    @Test
    public void testInsertarReservaBono() throws SQLException {
        ReservaBono reservaBono = new ReservaBono(1, 1001, 3);  // Ejemplo de IdReserva = 1, IdBono = 1001, NumSesion = 3
        reservaBonoDAO.insertarReservaBono(reservaBono);

        ReservaBono reservaBonoRecuperado = reservaBonoDAO.obtenerReservaBono(1, 1001);
        assertNotNull(reservaBonoRecuperado);
        assertEquals(reservaBono.getIdReserva(), reservaBonoRecuperado.getIdReserva());
        assertEquals(reservaBono.getIdBono(), reservaBonoRecuperado.getIdBono());
    }

    @Test
    public void testObtenerReservasBonos() throws SQLException {
        List<ReservaBono> lista = reservaBonoDAO.obtenerReservasBonos();
        assertTrue(lista.size() > 0, "La lista de reserva-bono no debería estar vacía.");
    }

    @Test
    public void testActualizarReservaBono() throws SQLException {
        ReservaBono reservaBono = new ReservaBono(1, 1001, 3);
        reservaBonoDAO.insertarReservaBono(reservaBono);

        reservaBono.setNumSesion(5);
        reservaBonoDAO.actualizarReservaBono(reservaBono);

        ReservaBono reservaBonoActualizado = reservaBonoDAO.obtenerReservaBono(1, 1001);
        assertEquals(5, reservaBonoActualizado.getNumSesion());
    }

    @Test
    public void testEliminarReservaBono() throws SQLException {
        ReservaBono reservaBono = new ReservaBono(1, 1001, 3);
        reservaBonoDAO.insertarReservaBono(reservaBono);

        reservaBonoDAO.eliminarReservaBono(1, 1001);
        ReservaBono reservaBonoEliminado = reservaBonoDAO.obtenerReservaBono(1, 1001);
        assertNull(reservaBonoEliminado);
    }
}
