package data.dao;

import business.materiales.MaterialDTO;
import common.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public MaterialDTO obtenerMaterialPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Material WHERE Id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MaterialDTO(
                            rs.getInt("Id"),
                            rs.getString("tipo"),
                            rs.getBoolean("UsoInterior"),
                            rs.getString("estado")
                    );
                }
            }
        }
        return null;
    }

    public List<MaterialDTO> listarMateriales() throws SQLException {
        List<MaterialDTO> materiales = new ArrayList<>();
        String sql = "SELECT * FROM Material";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                materiales.add(new MaterialDTO(
                        rs.getInt("Id"),
                        rs.getString("tipo"),
                        rs.getBoolean("UsoInterior"),
                        rs.getString("estado")
                ));
            }
        }
        return materiales;
    }

    public void insertarMaterial(MaterialDTO material) throws SQLException {
        String sql = "INSERT INTO Material (tipo, UsoInterior, estado) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, material.getTipo());
            stmt.setBoolean(2, material.isUsoInterior());
            stmt.setString(3, material.getEstado());
            stmt.executeUpdate();
        }
    }
}
