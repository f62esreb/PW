package dto;

public class Material {
    private int id;
    private String tipo; // 'pelotas', 'canastas', 'conos'
    private boolean interior; // true o false
    private String estado; // 'disponible', 'reservado', 'mal_estado'

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isInterior() {
        return interior;
    }

    public void setInterior(boolean interior) {
        this.interior = interior;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Material [id=" + id + ", tipo=" + tipo + ", interior=" + interior + ", estado=" + estado + "]";
    }
}
