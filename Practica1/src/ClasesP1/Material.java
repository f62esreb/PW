package ClasesP1;

/**
 * Clase que representa el material que puede ser 
 * reservado junto a la pista.
 */
public class Material {
	
	
	//Atributos de la clase.
	
	private int idMaterial;
	
	private tipoMaterial tipo; 
	
	private boolean usoInterior; 
	
	private estadoMaterial estado;
	
	//Enumeración para el tipo de material.
	public enum tipoMaterial{
		
		PELOTAS, CANASTAS, CONOS
	}
	
	
	//Enumeración para el estado del material.
	public enum estadoMaterial{
		
		DISPONIBLE, RESERVADO, MAL_ESTADO
	}
	
	//Métodos.
	
	/**
	 * Constructor vacío.
	 */
	
	public Material() {
		
	}
	
	/**
	 * Constructor parametrizado.
	 * @param idMaterial El identificador del material.
	 * @param tipo El tipo de material.
	 * @param usoInterior true si se usa en interior, false para exterior.
	 * @param estado Indica si el material está disponible, reservado o en mal estado.
	 * 
	 */
	public Material(int idMaterial, tipoMaterial tipo, boolean usoInterior, estadoMaterial estado) {
		
		this.idMaterial = idMaterial;
		this.tipo = tipo;
		this.usoInterior = usoInterior;
		this.estado = estado;
		
	}
	
	/**
	 * Obtiene el identificador del material.
	 * @return identificador del material.
	 */
	public void getId() {
		
		return idMaterial;
	}
	
	/**
	 * Establece el identificador del material.
	 * @param idNuevo El nuevo identificador del material.
	 */
	public void setId(int idNuevo) {
		
		thid.idMaterial = idNuevo;
		
	}
	
	/**
	 * Obtiene el tipo de material.
	 * @return el tipo de material ( pelotas, canastas, conos).
	 */
	public tipoMaterial getTipo() {
		
		return tipo;
		
	}
	void
	/**
	 * Establece el tipo de material.
	 * @param tipoNuevo El nuevo tipo de material.
	 */
	public void setTipo(tipoMaterial tipoNuevo) {
		
		this.tipo = tipoNuevo;
		
	}
	
	/**
	 * Verifica si el material es para uso en interior.
	 * @return true si es para interior, false si es para exterior.
	 */
	public boolean getUsoInterior() {
		
		return usoInterior;
		
	}
	
	/**
	 * Establece si el material es para uso interior o exterior.
	 * @param usoInterior true si es para interior, false si es para exterior.
	 */
	public void setUsoInterior(boolean nuevoUso) {
		
		this.usoInterior = nuevoUso;
		
	}
	
	/**
	 * Obtiene el estado del material.
	 * @return estado del material ( disponible, reservado, mal estado).
	 */
	public estadoMaterial getEstado() {
		
		return estado;
		
	}
	
	/**
	 * Establece el estado del material.
	 * @param estadoNuevo El nuevo estado del material.
	 */
	
	public void setEstado(estadoMaterial nuevoEstado) {
		
		this.estado = nuevoEstado;
		
	}
	
	/**
	 * Devuelve una cadena de texto con la información del material.
	 * @return Información del material: idMaterial, tipoMaterial, usoInterior y estadoMaterial.
	 */
	@Override
	public String toString() {
		
		return "Material: " +
				"identificador = " + this.idMaterial +
				", tipo de material = " + this.tipo +
				", uso = " + (usoInterior ? "Interior" : "Exterior") + 
				", estado del material = " + estado;
	}
}
