package data.dao;

import dto.ReservaBono;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaBonoDAO {

    // Insertar una relación
    public void insertarReservaBono(ReservaBono reservaBono) throws SQLException {
        String sql = "INSERT INTO ReservaBono (IdReserva, IdBono, NumSesion) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, reservaBono.getIdReserva());
            ps.setInt(2, reservaBono.getIdBono());
            ps.setInt(3, reservaBono.getNumSesion());
            ps.executeUpdate();
        }
    }

    // Obtener todas las relaciones para una reserva
    public List<ReservaBono> obtenerBonosPorReserva(int idReserva) throws SQLException {
        String sql = "SELECT * FROM ReservaBono WHERE IdReserva = ?";
        List<ReservaBono> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReservaBono rb = new ReservaBono(
                            rs.getInt("IdReserva"),
                            rs.getInt("IdBono"),
                            rs.getInt("NumSesion")
                    );
                    lista.add(rb);
                }
            }
        }
        return lista;
    }

    // Obtener todas las relaciones para un bono
    public List<ReservaBono> obtenerReservasPorBono(int idBono) throws SQLException {
        String sql = "SELECT * FROM ReservaBono WHERE IdBono = ?";
        List<ReservaBono> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBono);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReservaBono rb = new ReservaBono(
                            rs.getInt("IdReserva"),
                            rs.getInt("IdBono"),
                            rs.getInt("NumSesion")
                    );
                    lista.add(rb);
                }
            }
        }
        return lista;
    }

    // Actualizar número de sesiones
    public void actualizarNumSesion(int idReserva, int idBono, int numSesion) throws SQLException {
        String sql = "UPDATE ReservaBono SET NumSesion = ? WHERE IdReserva = ? AND IdBono = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, numSesion);
            ps.setInt(2, idReserva);
            ps.setInt(3, idBono);
            ps.executeUpdate();
        }
    }

    // Eliminar una relación
    public void eliminarRelacion(int idReserva, int idBono) throws SQLException {
        String sql = "DELETE FROM ReservaBono WHERE IdReserva = ? AND IdBono = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idReserva);
            ps.setInt(2, idBono);
            ps.executeUpdate();
        }
    }
}
