package dto;

public class PistaMaterial {
    private int idPista;
    private int idMaterial;
    private int cantidad;

    // Constructor
    public PistaMaterial(int idPista, int idMaterial, int cantidad) {
        this.idPista = idPista;
        this.idMaterial = idMaterial;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PistaMaterial [idPista=" + idPista + ", idMaterial=" + idMaterial + ", cantidad=" + cantidad + "]";
    }
}
