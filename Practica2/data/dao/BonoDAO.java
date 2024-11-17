package data.dao;

import java.sql.*;
import java.util.Properties;
import data.DatabaseConnection;
import dto.Bono;


/**
 * Clase DAO para el tratamiento de bonos de la BD
 */

public class BonoDAO {

	/*
	 * Lista bonos 
	 */
	
	public static void ListaBonos(){
		
		Statement stmt = null;
		
		try {
			Connection conn = Gestorbd.getConnection();
			Properties sql_properties = Gestorbd.getSQLProperties();
			String Statement = sql_properties.getProperty("ListBono");
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Statement);
			
			while(rs.next()) {
				
				int id = rs.getInt("ID");
				int descuento = rs.getInt("descuento");
				Date FechaCaducidad = rs.getDate("FechaCaducidad");
				Date FechaInicio = rs.getInt("FechaInicio");
				
				System.out.println(id+" "+descuento+" "+FechaInicio+" "+FechaCaducidad);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
	/*Guardar bono*/
	
	public static void newBono(int Id, int IdJugador, Date FechaCaducidad, int FechaInicio) {
			try {
			//Conexion
			Gestorbd gestorbd = Gestorbd.getInstance();
			Connection conn = Gestorbd.getConnection();
			//Carga del fichero sql.properties
			Properties sql_properties = Gestorbd.getSQLProperties();
			String Statement = sql_properties.getProperty("Guardar_bono");
			
			PreparedStatement ds =conn.prepareStatement(Statement);
			
			
			ds.setInt(1,Id);
			ds.setInt(2,IdJugador);
			ds.setDate(3,FechaInicio);
			ds.setInt(4,FechaCaducidad);
			
			ds.executeUpdate();
			
		}catch(Exception e){System.out.println(e);}
		
	}
}