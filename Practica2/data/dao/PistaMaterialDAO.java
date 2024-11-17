package data.dao;

import dto.PistaMaterial;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PistaMaterialDAO {

    // Insertar una nueva relación Pista-Material
    public void insertarPistaMaterial(PistaMaterial pistaMaterial) throws SQLException {
        String query = "INSERT INTO PistaMaterial (IdPista, IdMaterial, Cantidad) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, pistaMaterial.getIdPista());
            statement.setInt(2, pistaMaterial.getIdMaterial());
            statement.setInt(3, pistaMaterial.getCantidad());
            statement.executeUpdate();
        }
    }

    // Obtener todas las pistas asociadas a un material
    public List<PistaMaterial> obtenerPistasPorMaterial(int idMaterial) throws SQLException {
        String query = "SELECT * FROM PistaMaterial WHERE IdMaterial = ?";
        List<PistaMaterial> lista = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idMaterial);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lista.add(new PistaMaterial(
                        resultSet.getInt("IdPista"),
                        resultSet.getInt("IdMaterial"),
                        resultSet.getInt("Cantidad")
                    ));
                }
            }
        }
        return lista;
    }

    // Obtener todos los materiales asociados a una pista
    public List<PistaMaterial> obtenerMaterialesPorPista(int idPista) throws SQLException {
        String query = "SELECT * FROM PistaMaterial WHERE IdPista = ?";
        List<PistaMaterial> lista = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPista);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lista.add(new PistaMaterial(
                        resultSet.getInt("IdPista"),
                        resultSet.getInt("IdMaterial"),
                        resultSet.getInt("Cantidad")
                    ));
                }
            }
        }
        return lista;
    }

    // Actualizar la cantidad en una relación Pista-Material
    public void actualizarCantidad(int idPista, int idMaterial, int nuevaCantidad) throws SQLException {
        String query = "UPDATE PistaMaterial SET Cantidad = ? WHERE IdPista = ? AND IdMaterial = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, nuevaCantidad);
            statement.setInt(2, idPista);
            statement.setInt(3, idMaterial);
            statement.executeUpdate();
        }
    }

    // Eliminar una relación Pista-Material
    public void eliminarRelacion(int idPista, int idMaterial) throws SQLException {
        String query = "DELETE FROM PistaMaterial WHERE IdPista = ? AND IdMaterial = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPista);
            statement.setInt(2, idMaterial);
            statement.executeUpdate();
        }
    }
}
