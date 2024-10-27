package ClasesP1;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona las pistas y materiales necesarios para dar servicio.
 */

public class GestorDePistas {

    //Atributos.
    private ArrayList<Pista> pistas;
    private ArrayList<Material> materiales;

    /**
     * Constructor vacío que inicializa las listas de pistas y materiales.
     */
    public GestorDePistas() {

        this.pistas = new ArrayList<>();
        this.materiales = new ArrayList<>();

    }

    /**
     * Método para crear una nueva pista y añadirla a la lista de pistas.
     * 
     * @param pista La pista a añadir.
     */
    public void crearPista(Pista pista) {

        this.pistas.add(pista);

    }

    /**
     * Método para crear un nuevo material y añadirlo a la lista de materiales.
     * 
     * @param material El material a añadir.
     */
    public void crearMaterial(Material material) {

        this.materiales.add(material);

    }

    /**
     * Método privado para buscar una pista por su nombre.
     * 
     * @param nombre El nombre de la pista a buscar.
     * @return La pista si se encuentra, null si no existe.
     */

    private Pista buscarPistaPorNombre(String nombre) {
        
        for (Pista pista : pistas) {
           
            if (pista.getNombre().equals(nombre)) {
            
                return pista;
            
            }
       
        }
        
        return null;
    }

    /**
     * Método privado para buscar un material por su ID.
     * 
     * @param id El ID del material a buscar.
     * @return El material si se encuentra, null si no existe.
     */
    private Material buscarMaterialPorId(int id) {
       
        for (Material material : materiales) {
           
            if (material.getId() == id) {
            
                return material;
           
            }
        
        }
        
        return null;
    
    }
    
    /**
     * Método para asociar un material a una pista disponible.
     * 
     * @param pistaNombre El nombre de la pista a la que se quiere asociar el material.
     * @param idMaterial El identificador del material a asociar.
     * @return true si el material se asocia correctamente, false en caso contrario.
     */
    public boolean asociarMaterialAPista(String pistaNombre, int idMaterial) {
        
        // Buscar la pista por nombre
        Pista pista = buscarPistaPorNombre(pistaNombre);

        if (pista == null || !pista.getPistaDisponible()) {

            return false; // La pista no existe o no está disponible
        
        }

        // Buscar el material por ID y verificar disponibilidad
        Material material = buscarMaterialPorId(idMaterial);

        if (material == null || material.getEstado() != Material.estadoMaterial.DISPONIBLE) {
            
            return false; // El material no existe o no está disponible
        
        }

        // Asociar material a la pista según las reglas de tipos y cantidades
        return pista.asociarMaterialAPista(material);

    }

    /**
     * Método para listar las pistas no disponibles.
     * 
     * @return Una lista de pistas que no están disponibles.
     */
    public List<Pista> listarPistasNoDisponibles() {

        List<Pista> pistasNoDisponibles = new ArrayList<>();

        for (Pista pista : pistas) {

            if (!pista.getPistaDisponible()) {

                pistasNoDisponibles.add(pista);

            }
        }

        return pistasNoDisponibles;
    }

    /**
     * Método para buscar pistas libres que soporten al menos un número mínimo de jugadores y que cumplan con un tipo específico.
     * 
     * @param tipoPista El tipo de pista requerido (interior o exterior).
     * @param numJugadores El número mínimo de jugadores que la pista debe soportar.
     * @return Una lista de pistas que cumplen con las condiciones.
     */
    public List<Pista> buscarPistasLibres(boolean tipoPista, int numJugadores) {
        
        List<Pista> pistasLibres = new ArrayList<>();
       
        for (Pista pista : pistas) {
            
            if (pista.getPistaDisponible() && pista.getTipoPista() == tipoPista && pista.getNMax() >= numJugadores) {
               
                pistasLibres.add(pista);
            
            }
        
        }
        
        return pistasLibres;
    }

}
