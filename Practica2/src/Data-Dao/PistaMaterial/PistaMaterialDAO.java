package data.dao;

import business.pista_material.PistaMaterialDTO;
import common.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PistaMaterialDAO {

    public List<PistaMaterialDTO> obtenerMaterialesPorPista(int pistaId) throws SQLException {
        List<PistaMaterialDTO> materiales = new ArrayList<>();
        String sql = "SELECT * FROM PistaMaterial WHERE PistaId = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pistaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    materiales.add(new PistaMaterialDTO(
                            rs.getInt("PistaId"),
                            rs.getInt("MaterialId"),
                            rs.getInt("Cantidad")
                    ));
                }
            }
        }
        return materiales;
    }

    public void insertarRelacion(PistaMaterialDTO pistaMaterial) throws SQLException {
        String sql = "INSERT INTO PistaMaterial (PistaId, MaterialId, Cantidad) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pistaMaterial.getPistaId());
            stmt.setInt(2, pistaMaterial.getMaterialId());
            stmt.setInt(3, pistaMaterial.getCantidad());
            stmt.executeUpdate();
        }
    }

    public void actualizarCantidad(int pistaId, int materialId, int nuevaCantidad) throws SQLException {
        String sql = "UPDATE PistaMaterial SET Cantidad = ? WHERE PistaId = ? AND MaterialId = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nuevaCantidad);
            stmt.setInt(2, pistaId);
            stmt.setInt(3, materialId);
            stmt.executeUpdate();
        }
    }

    public void eliminarRelacion(int pistaId, int materialId) throws SQLException {
        String sql = "DELETE FROM PistaMaterial WHERE PistaId = ? AND MaterialId = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pistaId);
            stmt.setInt(2, materialId);
            stmt.executeUpdate();
        }
    }
}
