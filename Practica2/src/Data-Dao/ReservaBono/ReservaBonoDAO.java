package data.dao;

import business.reserva_bono.ReservaBonoDTO;
import common.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaBonoDAO {

    public List<ReservaBonoDTO> obtenerBonosPorReserva(int reservaId) throws SQLException {
        List<ReservaBonoDTO> bonos = new ArrayList<>();
        String sql = "SELECT * FROM ReservaBono WHERE ReservaId = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bonos.add(new ReservaBonoDTO(
                            rs.getInt("ReservaId"),
                            rs.getInt("BonoId"),
                            rs.getInt("NumeroSesion")
                    ));
                }
            }
        }
        return bonos;
    }

    public void asociarReservaConBono(ReservaBonoDTO reservaBono) throws SQLException {
        String sql = "INSERT INTO ReservaBono (ReservaId, BonoId, NumeroSesion) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservaBono.getReservaId());
            stmt.setInt(2, reservaBono.getBonoId());
            stmt.setInt(3, reservaBono.getNumeroSesion());
            stmt.executeUpdate();
        }
    }

    public void eliminarRelacion(int reservaId, int bonoId) throws SQLException {
        String sql = "DELETE FROM ReservaBono WHERE ReservaId = ? AND BonoId = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservaId);
            stmt.setInt(2, bonoId);
            stmt.executeUpdate();
        }
    }
}
