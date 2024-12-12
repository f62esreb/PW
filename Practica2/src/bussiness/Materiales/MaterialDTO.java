package business.materiales;

public class MaterialDTO {
    private int id;
    private String tipo;
    private boolean usoInterior;
    private String estado;

    public MaterialDTO() {}

    public MaterialDTO(int id, String tipo, boolean usoInterior, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.usoInterior = usoInterior;
        this.estado = estado;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public boolean isUsoInterior() { return usoInterior; }
    public void setUsoInterior(boolean usoInterior) { this.usoInterior = usoInterior; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "MaterialDTO{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", usoInterior=" + usoInterior +
                ", estado='" + estado + '\'' +
                '}';
    }
}
