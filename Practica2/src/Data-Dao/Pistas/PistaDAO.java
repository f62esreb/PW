package data.dao;

import business.pistas.PistaDTO;
import common.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PistaDAO {

    public PistaDTO obtenerPistaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Pista WHERE Id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PistaDTO(
                            rs.getInt("Id"),
                            rs.getString("Nombre"),
                            rs.getString("Estado"),
                            rs.getBoolean("TipoInterior"),
                            rs.getDouble("Tamano"),
                            rs.getInt("NumeroMaxJugadores")
                    );
                }
            }
        }
        return null;
    }

    public List<PistaDTO> listarPistas() throws SQLException {
        List<PistaDTO> pistas = new ArrayList<>();
        String sql = "SELECT * FROM Pista";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pistas.add(new PistaDTO(
                        rs.getInt("Id"),
                        rs.getString("Nombre"),
                        rs.getString("Estado"),
                        rs.getBoolean("TipoInterior"),
                        rs.getDouble("Tamano"),
                        rs.getInt("NumeroMaxJugadores")
                ));
            }
        }
        return pistas;
    }

    public void insertarPista(PistaDTO pista) throws SQLException {
        String sql = "INSERT INTO Pista (Nombre, Estado, TipoInterior, Tamano, NumeroMaxJugadores) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pista.getNombre());
            stmt.setString(2, pista.getEstado());
            stmt.setBoolean(3, pista.isTipoInterior());
            stmt.setDouble(4, pista.getTamano());
            stmt.setInt(5, pista.getNumeroMaxJugadores());
            stmt.executeUpdate();
        }
    }
}
