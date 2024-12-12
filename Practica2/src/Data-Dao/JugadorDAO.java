package data.dao;

import business.jugadores.JugadorDTO;
import common.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {

    public JugadorDTO obtenerJugadorPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Jugador WHERE Id = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new JugadorDTO(
                            rs.getInt("Id"),
                            rs.getString("nombreCompleto"),
                            rs.getDate("FechaNacimiento"),
                            rs.getDate("FechaInscripcion"),
                            rs.getString("correo")
                    );
                }
            }
        }
        return null;
    }

    public List<JugadorDTO> listarJugadores() throws SQLException {
        List<JugadorDTO> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM Jugador";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                jugadores.add(new JugadorDTO(
                        rs.getInt("Id"),
                        rs.getString("nombreCompleto"),
                        rs.getDate("FechaNacimiento"),
                        rs.getDate("FechaInscripcion"),
                        rs.getString("correo")
                ));
            }
        }
        return jugadores;
    }

    public void insertarJugador(JugadorDTO jugador) throws SQLException {
        String sql = "INSERT INTO Jugador (nombreCompleto, FechaNacimiento, FechaInscripcion, correo) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jugador.getNombreCompleto());
            stmt.setDate(2, new java.sql.Date(jugador.getFechaNacimiento().getTime()));
            stmt.setDate(3, new java.sql.Date(jugador.getFechaInscripcion().getTime()));
            stmt.setString(4, jugador.getCorreo());
            stmt.executeUpdate();
        }
    }
}

