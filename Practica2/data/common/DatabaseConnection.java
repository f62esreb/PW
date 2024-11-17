package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {
    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    // Cargar las configuraciones desde config.properties
    static {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se pudo encontrar el archivo config.properties");
            }

            Properties properties = new Properties();
            properties.load(input);

            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            driver = properties.getProperty("db.driver");

            // Cargar el driver JDBC
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar las configuraciones de la base de datos", e);
        }
    }

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}