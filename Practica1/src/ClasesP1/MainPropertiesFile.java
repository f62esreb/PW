package ClasesP1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainPropertiesFile {

    public static void main(String[] args) {
        
        Properties prop = new Properties();
        String filename = "config/config.properties"; // Ruta al archivo de propiedades
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            prop.load(reader);
            
            // Obtener las rutas de los archivos de datos desde el archivo de propiedades
            String rutaPistas = prop.getProperty("ruta.pistas");
            String rutaUsuarios = prop.getProperty("ruta.usuarios");
            String rutaReservas = prop.getProperty("ruta.reservas");
            String rutaMateriales = prop.getProperty("ruta.materiales");
            String rutaBonos = prop.getProperty("ruta.bonos");
            
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de propiedades no se encontró en la ruta especificada: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de propiedades.");
            e.printStackTrace();
        }
    }
}

