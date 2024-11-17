package data.dao;

import dto.Jugador;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO {

    // Insertar un jugador
    public void insertarJugador(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO Jugador (DNI, NombreCompleto, FechaNacimiento, FechaInscripcion, Correo) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, jugador.getDni());
            ps.setString(2, jugador.getNombreCompleto());
            ps.setDate(3, new java.sql.Date(jugador.getFechaNacimiento().getTime()));
            ps.setDate(4, new java.sql.Date(jugador.getFechaInscripcion().getTime()));
            ps.setString(5, jugador.getCorreo());
            ps.executeUpdate();
        }
    }

    // Obtener todos los jugadores
    public List<Jugador> obtenerJugadores() throws SQLException {
        String sql = "SELECT * FROM Jugador";
        List<Jugador> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Jugador jugador = new Jugador(
                        rs.getInt("DNI"),
                        rs.getString("NombreCompleto"),
                        rs.getDate("FechaNacimiento"),
                        rs.getDate("FechaInscripcion"),
                        rs.getString("Correo")
                );
                lista.add(jugador);
            }
        }
        return lista;
    }

    // Obtener un jugador por DNI
    public Jugador obtenerJugadorPorDni(int dni) throws SQLException {
        String sql = "SELECT * FROM Jugador WHERE DNI = ?";
        Jugador jugador = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    jugador = new Jugador(
                            rs.getInt("DNI"),
                            rs.getString("NombreCompleto"),
                            rs.getDate("FechaNacimiento"),
                            rs.getDate("FechaInscripcion"),
                            rs.getString("Correo")
                    );
                }
            }
        }
        return jugador;
    }

    // Actualizar un jugador
    public void actualizarJugador(Jugador jugador) throws SQLException {
        String sql = "UPDATE Jugador SET NombreCompleto = ?, FechaNacimiento = ?, FechaInscripcion = ?, Correo = ? WHERE DNI = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, jugador.getNombreCompleto());
            ps.setDate(2, new java.sql.Date(jugador.getFechaNacimiento().getTime()));
            ps.setDate(3, new java.sql.Date(jugador.getFechaInscripcion().getTime()));
            ps.setString(4, jugador.getCorreo());
            ps.setInt(5, jugador.getDni());
            ps.executeUpdate();
        }
    }

    // Eliminar un jugador
    public void eliminarJugador(int dni) throws SQLException {
        String sql = "DELETE FROM Jugador WHERE DNI = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dni);
            ps.executeUpdate();
        }
    }
}
