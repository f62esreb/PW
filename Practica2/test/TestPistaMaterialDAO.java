package test;

import data.dao.PistaMaterialDAO;
import dto.PistaMaterial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPistaMaterialDAO {

    private PistaMaterialDAO pistaMaterialDAO;

    @BeforeEach
    public void setUp() {
        pistaMaterialDAO = new PistaMaterialDAO();
    }

    @AfterEach
    public void tearDown() {
        pistaMaterialDAO = null;
    }

    @Test
    public void testInsertarPistaMaterial() throws SQLException {
        PistaMaterial pistaMaterial = new PistaMaterial(1, 2, 10);  // Ejemplo de IdPista = 1, IdMaterial = 2, Cantidad = 10
        pistaMaterialDAO.insertarPistaMaterial(pistaMaterial);

        PistaMaterial pistaMaterialRecuperado = pistaMaterialDAO.obtenerPistaMaterial(1, 2);
        assertNotNull(pistaMaterialRecuperado);
        assertEquals(pistaMaterial.getIdPista(), pistaMaterialRecuperado.getIdPista());
        assertEquals(pistaMaterial.getIdMaterial(), pistaMaterialRecuperado.getIdMaterial());
    }

    @Test
    public void testObtenerPistasMaterial() throws SQLException {
        List<PistaMaterial> lista = pistaMaterialDAO.obtenerPistasMaterial();
        assertTrue(lista.size() > 0, "La lista de pista-material no debería estar vacía.");
    }

    @Test
    public void testActualizarPistaMaterial() throws SQLException {
        PistaMaterial pistaMaterial = new PistaMaterial(1, 2, 10);
        pistaMaterialDAO.insertarPistaMaterial(pistaMaterial);

        pistaMaterial.setCantidad(15);
        pistaMaterialDAO.actualizarPistaMaterial(pistaMaterial);

        PistaMaterial pistaMaterialActualizado = pistaMaterialDAO.obtenerPistaMaterial(1, 2);
        assertEquals(15, pistaMaterialActualizado.getCantidad());
    }

    @Test
    public void testEliminarPistaMaterial() throws SQLException {
        PistaMaterial pistaMaterial = new PistaMaterial(1, 2, 10);
        pistaMaterialDAO.insertarPistaMaterial(pistaMaterial);

        pistaMaterialDAO.eliminarPistaMaterial(1, 2);
        PistaMaterial pistaMaterialEliminado = pistaMaterialDAO.obtenerPistaMaterial(1, 2);
        assertNull(pistaMaterialEliminado);
    }
}
