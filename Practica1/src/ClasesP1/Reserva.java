package paquete;



import java.util.Date;

public abstract class Reserva {
    private String usuarioId;
    private Date fechaHora;
    private int duracion; // en minutos
    private String pistaId;
    private float precio;
    private float descuento;

    public Reserva() {
    }

    public Reserva(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento) {
        this.usuarioId = usuarioId;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.pistaId = pistaId;
        this.precio = precio;
        this.descuento = descuento;
    }

    // Getters y Setters
    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
    
    public Date getFechaHora() { return fechaHora; }
    public void setFechaHora(Date fechaHora) { this.fechaHora = fechaHora; }
    
    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    
    public String getPistaId() { return pistaId; }
    public void setPistaId(String pistaId) { this.pistaId = pistaId; }
    
    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }
    
    public float getDescuento() { return descuento; }
    public void setDescuento(float descuento) { this.descuento = descuento; }

    @Override
    public String toString() {
        return "Reserva para usuario " + usuarioId + ", Fecha y hora: " + fechaHora +
               ", Duración: " + duracion + " minutos, Pista: " + pistaId +
               ", Precio: " + precio + "€, Descuento: " + descuento + "€";
    }
}
