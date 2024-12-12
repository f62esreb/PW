package business.pista_material;

public class PistaMaterialDTO {
    private int pistaId;
    private int materialId;
    private int cantidad;

    public PistaMaterialDTO() {}

    public PistaMaterialDTO(int pistaId, int materialId, int cantidad) {
        this.pistaId = pistaId;
        this.materialId = materialId;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public int getPistaId() { return pistaId; }
    public void setPistaId(int pistaId) { this.pistaId = pistaId; }
    public int getMaterialId() { return materialId; }
    public void setMaterialId(int materialId) { this.materialId = materialId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "PistaMaterialDTO{" +
                "pistaId=" + pistaId +
                ", materialId=" + materialId +
                ", cantidad=" + cantidad +
                '}';
    }
}
