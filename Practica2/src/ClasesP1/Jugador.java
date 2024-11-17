package ClasesP1;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Clase que representa a una persona que va a hacer uso de las instalaciones
 * deportivas.
 */
public class Jugador {

	//Atributos.
	
	private String nombreJugador;
	private String apellidosJugador;
	private Date fechaNacimiento;
	private Date fechaInscripcion;
	private String correo;
	
	//Métodos
	
	/**
	 * Constructor vacío.
	 */
	public Jugador() {
		
		this.fechaInscripcion = new Date(); //Inicializamos con fecha actual del sistema.
	
	}
	
	/**
	 * Constructor parametrizado.
	 * @param nombreJugador el nombre del jugador.
	 * @param apellidosJugador los apellidos del jugador.
	 * @param fechaNacimiento fecha de nacimiento del jugador.
	 * @param correo correo electrónico del jugador.
	 */
	public Jugador(String nombreJugador, String apellidosJugador, Date fechaNacimiento, String correo) {
		
		this.nombreJugador = nombreJugador;
		this.apellidosJugador = apellidosJugador;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.fechaInscripcion = new Date();
	
	}
	
	/**
	 * Obtiene el nombre del jugador.
	 * @return el nombre del jugador.
	 */
	public String getNombre() {
		
		return nombreJugador;
	}
	
	/**
	 * Establece el nombre del jugador.
	 * @param nombreNuevo el nombre del jugador.
	 */
	public void setNombre(String nombreNuevo) {
		
		this.nombreJugador = nombreNuevo;
		
	}
	
	/**
	 * Obtiene los apellidos del jugador.
	 * @return los apellidos del jugador.
	 */
	public String getApellidos() {
		
		return apellidosJugador;
		
	}
	
	/**
	 * Establece los apellidos del jugador.
	 * @param apellidosNuevos los apellidos del jugador.
	 */
	public void setApellidos(String apellidosNuevos) {
		
		this.apellidosJugador = apellidosNuevos;
		
	}
	
	/**
	 * Obtiene la fecha de nacimiento del jugador.
	 * @return la fecha de nacimiento del jugador.
	 */
	public Date getFechaNacimiento() {
		
		return fechaNacimiento;
		
	}
	
	/**
	 * Establece la fecha de nacimiento del jugador.
	 * @param nuevaFechaNacimiento la fecha de nacimiento del jugador.
	 */
	public void setFechaNacimiento(Date nuevaFechaNacimiento) {
		
		this.fechaNacimiento = nuevaFechaNacimiento;
		
	}
	
	/**
	 * Obtiene la fecha de inscripción del jugador.
	 * @return fecha de inscripción del jugador.
	 */
	public Date getFechaInscripcion() {
		
		return fechaInscripcion;
		
	}
	
	/**
	 * Establece la fecha de inscripción del jugador.
	 * @param nuevaFechaInscripcion fecha de inscripción del jugador.
	 */
	public void setFechaInscripcion(Date nuevaFechaInscripcion) {
		
		this.fechaInscripcion = nuevaFechaInscripcion;
		
	}
	
	/**
	 * Obtiene el correo electrónico del jugador.
	 * @return el correo electrónico del jugador.
	 */
	public String getCorreo() {
		
		return correo;
		
	}
	
	/**
	 * Establece el correo electrónico del jugador.
	 * @param nuevoCorreo correo electrónico del jugador.
	 */
	public void setCorreo(String nuevoCorreo) {
		
		this.correo = nuevoCorreo;
		
	}
	
	/**
	 * Imprime información sobre el jugador.
	 * @return información sobre el jugador: nombre, apellidos, fecha de nacimiento,
	 * fecha de inscripción, correo electrónico.
	 */
	@Override
    public String toString() {
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        return "Jugador: " +
                "nombre = " + nombreJugador +
                "apellidos = "+ apellidosJugador +
                ", fechaNacimiento = " + dateFormat.format(fechaNacimiento) +
                ", fechaInscripcion = " + dateFormat.format(fechaInscripcion) +
                ", correoElectronico = " + correo;
    }
	
	public int calcularAntiguedad() {
		
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaInscripcion = Calendar.getInstance();
		
		fechaInscripcion.setTime(this.fechaInscripcion);
		
		int ant = fechaActual.get(Calendar.YEAR) - fechaInscripcion.get(Calendar.YEAR);
		
		//Si aun no ha llegado la fecha de hacer un año este año, restamos 1.
		if(fechaActual.get(Calendar.DAY_OF_YEAR) < fechaInscripcion.get(Calendar.DAY_OF_YEAR)){
			
			ant--;
			
		}
		
		return ant;
	}
}
