package dto;

public class Pista {
    private int id;
    private boolean disponibilidad; // true o false
    private boolean interior; // true o false
    private String tamaño; // 'MINIBASKET', 'ADULTOS', 'TRES_VS_TRES'
    private int nMaximo; // Número máximo de personas

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean isInterior() {
        return interior;
    }

    public void setInterior(boolean interior) {
        this.interior = interior;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public int getnMaximo() {
        return nMaximo;
    }

    public void setnMaximo(int nMaximo) {
        this.nMaximo = nMaximo;
    }

    @Override
    public String toString() {
        return "Pista [id=" + id + ", disponibilidad=" + disponibilidad + ", interior=" + interior + 
                ", tamaño=" + tamaño + ", nMaximo=" + nMaximo + "]";
    }
}
