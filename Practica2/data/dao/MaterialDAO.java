package data.dao;

import data.DatabaseConnection;
import dto.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    // Método para insertar un nuevo material
    public void insertarMaterial(Material material) throws SQLException {
        String sql = "INSERT INTO Material (id, tipo, interior, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, material.getId());
            stmt.setString(2, material.getTipo());
            stmt.setBoolean(3, material.isInterior());
            stmt.setString(4, material.getEstado());
            stmt.executeUpdate();
        }
    }

    // Método para obtener todos los materiales
    public List<Material> obtenerMateriales() throws SQLException {
        String sql = "SELECT * FROM Material";
        List<Material> materiales = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Material material = new Material();
                material.setId(rs.getInt("id"));
                material.setTipo(rs.getString("tipo"));
                material.setInterior(rs.getBoolean("interior"));
                material.setEstado(rs.getString("estado"));
                materiales.add(material);
            }
        }
        return materiales;
    }

    // Método para actualizar un material
    public void actualizarMaterial(Material material) throws SQLException {
        String sql = "UPDATE Material SET tipo = ?, interior = ?, estado = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, material.getTipo());
            stmt.setBoolean(2, material.isInterior());
            stmt.setString(3, material.getEstado());
            stmt.setInt(4, material.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar un material
    public void eliminarMaterial(int id) throws SQLException {
        String sql = "DELETE FROM Material WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
