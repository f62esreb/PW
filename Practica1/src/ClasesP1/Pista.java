package ClasesP1;

import ClasesP1.Material.estadoMaterial;
import ClasesP1.Material.tipoMaterial;
import java.util.*;

/**
 * Clase que representa una pista de baloncesto disponible
 * en las instalaciones.
 * */

public class Pista {

	//Atributos de la clase.
	
	private String nombrePista;
	
	private boolean pistaDisponible;
	
	private boolean pistaInterior;
	
	private tamPista tam;
	
	private int nMax;

	private ArrayList<Material> materiales;
	
	
	public enum tamPista{
		
		MIINIBASKET, ADULTOS, _3VS3
	
	}
	
	//Métodos.
	
	/**
	 * Constructor vacío.
	 * */
	
	public Pista() {
		
		this.materiales = new ArrayList<>();
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param nombrePista El nombre de la pista.
	 * @param pistaDisponible true si está disponible, false si no lo está.
	 * @param pistaInterior true si es pista interior, false si es exterior.
	 * @param tam El tamaño de la pista
	 * @param nMax El número máximo de jugadores.
	 * */
	public Pista(String nombrePista, boolean pistaDisponible, boolean pistaInterior, tamPista tam, int nMax ) {
		
		this.nombrePista = nombrePista;
		this.pistaDisponible = pistaDisponible;
		this.pistaInterior = pistaInterior;
		this.tam = tam;
		this.nMax = nMax;
		this.materiales = new ArrayList<>();
		
	}
	
	/**
	 * Obtiene el nombre de la pista.
	 * @return el nombre de la pista.
	 * */
	public String getNombre() {
		
		return nombrePista;
		
	}
	
	/**
	 * Establece el nombre de la pista.
	 * @param nombreNuevo El nuevo nombre de la pista.
	 * */
	public void setNombre(String nombreNuevo) {
		
		this.nombrePista = nombreNuevo;
	}
	
	/**
	 * Obtiene el estado de la pista.
	 * @return el estado de la pista.
	 * */
	public boolean getPistaDisponible() {
		
		return pistaDisponible;
		
	}
	
	/**
	 * Establece el estado de la pista.
	 * @param estadoNuevo El nuevo nombre de la pista.
	 * */
	public void setEstadoPista(String estadoNuevo) {
		
		this.pistaDisponible = estadoNuevo;
		
	}
	
	/**
	 * Obtiene el tipo de la pista.
	 * @return true si es interior, false si es exterior.
	 * */
	public boolean getTipoPista() {
		
		return pistaInterior;
		
	}
	
	/**
	 * Establece el tipo de la pista.
	 * @param tipoNuevo El nuevo tipo de la pista.
	 * */
	public void setTipoPista(boolean tipoNuevo) {
		
		this.pistaInterior = tipoNuevo;
	}
	
	/**
	 * Obtiene el ntamaño de la pista.
	 * @return el tamaño  de la pista (Minibasket, Adultos, 3vs3).
	 * */
	public tamPista getTam() {
		
		return tam;
		
	}
	
	/**
	 * Establece el tamaño de la pista.
	 * @param tamNuevo El nuevo tamaño de la pista.
	 * */
	public void setTam(tamPista tamNuevo) {
		
		this.tam = tamNuevo;
		
	}
	
	/**
	 * Obtiene el máximo de jugadores.
	 * @return el máximo de jugadores.
	 * */
	public int getNMax() {
		
		return nMax;
		
	}
	
	/**
	 * Establece el máximo de jugadores.
	 * @param nMaxNuevo el nuevo máximo de jugadores.
	 * */
	public void setNMax(int nMaxNuevo) {
		
		this.nMax = nMaxNuevo;
		
	}
	
	/**
	 * Obtiene la lista de materiales.
	 * @return la lista de materiales.
	 * */
	public ArrayList<Material> getMaterialesLista() {
		
		return materiales;
		
	}
	
	/**
	 * Establece una lista de materiales.
	 * @param materialesNuevo nueva lista de materiales.
	 * */
	public void setMaterialesLista(ArrayList<Material> materialesNuevo) {
		
		this.materiales = materialesNuevo;
		
	}
	
	/**
	 * Imprime información sobre la psita.
	 * @return información sobre la pista: nombre, estado, tipo, tamaño, número máximo de jugadores, materiales.
	 * */
	@Override
	public String toString() {
		
		return "Pista: " +
                "nombre ='" + nombrePista + '\'' +
                ", estadoDisponible = " + (pistaDisponible ? "Disponible" : "No disponible") +
                ", esInterior = " + (pistaInterior ? "Interior" : "Exterior") +
                ", tamaño = " + tam +
                ", maxJugadores = " + nMax +
                ", materiales = " + materiales.size() + " materiales asociados";
	}
	
	/**
	 * Método que devuelve el subconjunto de materiales que no están en mal estado ni reservados.
	 * @return materiales disponibles.
	 * */
	public ArrayList<Material> consultarMaterialesDisponibles(){
		
		ArrayList<Material> disponibles = new ArrayList<>();
		
		for(Material material : materiales) {
			
			if(material.getEstado() == Material.estadoMaterial.DISPONIBLE) {
				
				disponibles.add(material);
				
			}
		}
		
		return disponibles;
		
	}
	
	/**
	 * Método que asocia un material a la pista.
	 * Si la pista es exterior sólo se pueden añadir materiales de exterior.
	 * Si la pista es interior se pueden añadir ambos tipos de materiales.
	 * Límite en la cantidad de pelotas (12), canastas (2) y conos (20).
	 * 
	 * @param material El material a asociar.
	 * @return true si se puede asociar correctamente, false en caso contrario.
	 * */
	public boolean asociarMaterialAPista(Material material) {
		
		int pelotas = 0, canastas = 0, conos = 0;
		
		for(Material mat : materiales) {
			
			switch(mat.getTipo()) {
			
			case PELOTAS:
				
				pelotas++;
				break;
				
			case CANASTAS:
				
				canastas++;
				break;
			
			case CONOS:
				
				conos++;
				break;
				
			}
			
		}
		
		if(pistaInterior ||(!pistaInterior && !material.getUsoInterior())){
			
			switch(material.getTipo()) {
			
			case PELOTAS:
				
				if(pelotas >= 12) {
					
					return false; //Límite alcanzado.
				}
				break;
			
			case CANASTAS:
				
				if(canastas >= 2) {
					
					return false; //Límite alcanzado.
					
				}
				break;
			
			case CONOS:
				
				if(conos >=20) {
					
					return false; //Límite alcanzado.
					
				}
				break;
				
			}
			
			materiales.add(material);
			return true;
			
			
		}
		
		return false;
		
	}
	
}