package dto;

import java.util.Date;

public class Jugador {
    private int dni;
    private String nombreCompleto;
    private Date fechaNacimiento;
    private Date fechaInscripcion;
    private String correo;

    // Constructor
    public Jugador(int dni, String nombreCompleto, Date fechaNacimiento, Date fechaInscripcion, String correo) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaInscripcion = fechaInscripcion;
        this.correo = correo;
    }

    // Getters y Setters
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Jugador [dni=" + dni + ", nombreCompleto=" + nombreCompleto + ", fechaNacimiento=" + fechaNacimiento
                + ", fechaInscripcion=" + fechaInscripcion + ", correo=" + correo + "]";
    }
}
