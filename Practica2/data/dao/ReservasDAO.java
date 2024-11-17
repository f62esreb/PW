package data.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import data.DatabaseConnection;
import dto.Reservas;


public class ReservasDAO{
	private static java.util.Properties prop;
	protected  String url;
	protected  String user;
	protected  String password;
	private Gestorbd bd; 
	private static Connection conn;

	public ReservasDAO(String url, String user,String pass,java.util.Properties prop) {
		this.url=url;
		this.user=user;
		this.password=pass;
		this.prop = prop;
		this.bd =new Gestorbd (this.url, this.user, this.password);
		this.conn =  bd.getConnection();
		
	}
	/*
	 * Funcion que lista todas las reserva
	 */
		public ArrayList<ReservasDTO> ListaReservas() {
			Statement stmt = null;
			ArrayList<ReservasDTO> listReservas =new ArrayList<ReservasDTO>();
			try {
				Connection conn = Gestorbd.getConnection();
				Properties sql_properties = Gestorbd.getSQLProperties();
				String Statement = sql_properties.getProperty("ListReservas");
				
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Statement);
				
				while(rs.next()) {
					int idreserva=rs.getInt("Id");
					int iduser=rs.getInt("IdJugador");
					int idpistas = rs.getInt("IdPista");
					Date Fecha=rs.getDate("FechaHora");
					int duracion=rs.getInt("Duración");
					float precio=rs.getFloat("Precio");
					float descuento=rs.getFloat("Descuento");
					String tipo=rs.getString("TipoReserva");
					
				}
			}catch(Exception e) {
				System.out.println(e);
			}
			return listReservas;
		}
	
	/*
	 * Funcion que lista las reservas futuras
	 */
	public static void ListaReservasFut() {
		Statement stmt = null;
	
		try {
			String Statement = prop.getProperty("ListReservasFut");
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Statement);
			
			while(rs.next()) {
				int id_reserva=rs.getInt("Id");
				int id_user=rs.getInt("IdJugador");
				int id_pistass = rs.getInt("IdPista");
				Date fecha_reserva=rs.getDate("FechaHora");
				int duracion=rs.getInt("Duración");
				float precio=rs.getDouble("Precio");
				float descuento=rs.getDouble("descuento");
				String tipo_reserva=rs.getString("TipoReserva");
				
				System.out.println(id_reserva+" "+id_user+" "+id_pistass+" "+fecha_reserva+" "+duracion+" "+precio+" "+descuento+" "+tipo_reserva);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	/*
	 * Funcion que crea una reserva
	 */
	public static boolean newReserva(String id_user, String id_pistas,  Date fecha_reserva, Time hora_reserva,int duracion,double precio, double descuento,String tipo_reserva,
			int max_niños, int max_adultos, int max_participantes) {
		try {
		String Statement = prop.getProperty("Guardar_Reserva");
		
		PreparedStatement ds =conn.prepareStatement(Statement);
		
		ds.setString(1,id_user);
		ds.setString(2,id_pistas);
		ds.setDate(3, fecha_reserva);
		ds.setInt(4, duracion);
		ds.setFloat(5, precio);
		ds.setFloat(6, descuento);
		ds.setString(7, tipo_reserva);

		
		ds.executeUpdate();
		return true;
	}catch(Exception e){System.out.println(e);}
		return false;
	}
	
	/*
	 * Funcion que elimina una reserva
	 */
	
	public boolean deleteReserva(int id) {
		try {
			String Statement = prop.getProperty("Delete_reserva");
			
			PreparedStatement ds =conn.prepareStatement(Statement);
			
			ds.setInt(1,id);
			ds.executeUpdate();
			return true;
			
		}catch(Exception e) {System.out.println(e);}
		return false;
	}
	
	
}