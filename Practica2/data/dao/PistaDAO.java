package data.dao;

import data.DatabaseConnection;
import dto.Pista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PistaDAO {

    // Método para insertar una nueva pista
    public void insertarPista(Pista pista) throws SQLException {
        String sql = "INSERT INTO Pista (id, disponibilidad, interior, tamaño, N_Máximo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pista.getId());
            stmt.setBoolean(2, pista.isDisponibilidad());
            stmt.setBoolean(3, pista.isInterior());
            stmt.setString(4, pista.getTamaño());
            stmt.setInt(5, pista.getnMaximo());
            stmt.executeUpdate();
        }
    }

    // Método para obtener todas las pistas
    public List<Pista> obtenerPistas() throws SQLException {
        String sql = "SELECT * FROM Pista";
        List<Pista> pistas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pista pista = new Pista();
                pista.setId(rs.getInt("id"));
                pista.setDisponibilidad(rs.getBoolean("disponibilidad"));
                pista.setInterior(rs.getBoolean("interior"));
                pista.setTamaño(rs.getString("tamaño"));
                pista.setnMaximo(rs.getInt("N_Máximo"));
                pistas.add(pista);
            }
        }
        return pistas;
    }

    // Método para actualizar una pista
    public void actualizarPista(Pista pista) throws SQLException {
        String sql = "UPDATE Pista SET disponibilidad = ?, interior = ?, tamaño = ?, N_Máximo = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, pista.isDisponibilidad());
            stmt.setBoolean(2, pista.isInterior());
            stmt.setString(3, pista.getTamaño());
            stmt.setInt(4, pista.getnMaximo());
            stmt.setInt(5, pista.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar una pista
    public void eliminarPista(int id) throws SQLException {
        String sql = "DELETE FROM Pista WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
