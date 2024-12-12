package data.dao;

import business.reservas.ReservaDTO;
import common.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public ReservaDTO obtenerReservaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Reserva WHERE Id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ReservaDTO(
                            rs.getInt("Id"),
                            rs.getInt("UsuarioId"),
                            rs.getInt("PistaId"),
                            rs.getTimestamp("FechaHora"),
                            rs.getInt("DuracionMinutos"),
                            rs.getDouble("Precio"),
                            rs.getDouble("Descuento"),
                            rs.getString("TipoReserva"),
                            rs.getInt("NumeroNinos"),
                            rs.getInt("NumeroAdultos"),
                            rs.getInt("NumeroAdultosParticipantes"),
                            rs.getInt("NumeroNinosParticipantes"),
                            rs.getBoolean("EsBono"),
                            rs.getObject("IdBono", Integer.class),
                            rs.getObject("NumeroSesion", Integer.class)
                    );
                }
            }
        }
        return null;
    }

    public List<ReservaDTO> listarReservas() throws SQLException {
        List<ReservaDTO> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Reserva";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                reservas.add(new ReservaDTO(
                        rs.getInt("Id"),
                        rs.getInt("UsuarioId"),
                        rs.getInt("PistaId"),
                        rs.getTimestamp("FechaHora"),
                        rs.getInt("DuracionMinutos"),
                        rs.getDouble("Precio"),
                        rs.getDouble("Descuento"),
                        rs.getString("TipoReserva"),
                        rs.getInt("NumeroNinos"),
                        rs.getInt("NumeroAdultos"),
                        rs.getInt("NumeroAdultosParticipantes"),
                        rs.getInt("NumeroNinosParticipantes"),
                        rs.getBoolean("EsBono"),
                        rs.getObject("IdBono", Integer.class),
                        rs.getObject("NumeroSesion", Integer.class)
                ));
            }
        }
        return reservas;
    }

    public void insertarReserva(ReservaDTO reserva) throws SQLException {
        String sql = "INSERT INTO Reserva (UsuarioId, PistaId, FechaHora, DuracionMinutos, Precio, Descuento, TipoReserva, " +
                     "NumeroNinos, NumeroAdultos, NumeroAdultosParticipantes, NumeroNinosParticipantes, EsBono, IdBono, NumeroSesion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getUsuarioId());
            stmt.setInt(2, reserva.getPistaId());
            stmt.setTimestamp(3, new Timestamp(reserva.getFechaHora().getTime()));
            stmt.setInt(4, reserva.getDuracionMinutos());
            stmt.setDouble(5, reserva.getPrecio());
            stmt.setDouble(6, reserva.getDescuento());
            stmt.setString(7, reserva.getTipoReser
