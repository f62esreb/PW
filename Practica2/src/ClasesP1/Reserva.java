package ClasesP1;

import java.util.Date;

public abstract class Reserva {
    private int id;
    private String usuarioId;
    private Date fechaHora;
    private int duracion;
    private String pistaId;
    private float precio;
    private float descuento;

    // Constructor sin parámetros
    public Reserva() {}

    // Constructor con parámetros específicos (sin `id`)
    public Reserva(String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento) {
        this.usuarioId = usuarioId;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.pistaId = pistaId;
        this.precio = precio;
        this.descuento = descuento;
    }

    // Constructor con `id` incluido (opcional, en caso de necesitar ambos)
    public Reserva(int id, String usuarioId, Date fechaHora, int duracion, String pistaId, float precio, float descuento) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.fechaHora = fechaHora;
        this.duracion = duracion;
        this.pistaId = pistaId;
        this.precio = precio;
        this.descuento = descuento;
    }

    // Método getId() necesario para evitar errores
    public int getId() { return id; }

    // Getters y setters adicionales
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
