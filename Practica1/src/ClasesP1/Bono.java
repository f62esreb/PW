package paquete;

import java.util.ArrayList;
import java.util.Date;

public class Bono {
    private int idBono; 
    private String idUsuario; 
    private ArrayList<Reserva> reservas; 
    private Date fechaPrimeraReserva; 
    private Date fechaCaducidad; 
    private double precioReducido; 

    // Constructor vacío
    public Bono() {
        this.reservas = new ArrayList<>();
    }

    // Constructor parametrizado
    public Bono(int idBono, String idUsuario, Date fechaPrimeraReserva, double precioReducido) {
        this.idBono = idBono;
        this.idUsuario = idUsuario;
        this.fechaPrimeraReserva = fechaPrimeraReserva;
        this.fechaCaducidad = calcularFechaCaducidad(fechaPrimeraReserva);
        this.precioReducido = precioReducido;
        this.reservas = new ArrayList<>();
    }

    
    public int getIdBono() {
        return idBono;
    }

    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Date getFechaPrimeraReserva() {
        return fechaPrimeraReserva;
    }

    public void setFechaPrimeraReserva(Date fechaPrimeraReserva) {
        this.fechaPrimeraReserva = fechaPrimeraReserva;
        this.fechaCaducidad = calcularFechaCaducidad(fechaPrimeraReserva);
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public double getPrecioReducido() {
        return precioReducido;
    }

    public void setPrecioReducido(double precioReducido) {
        this.precioReducido = precioReducido;
    }

    private Date calcularFechaCaducidad(Date fechaInicio) {
        long unAnioEnMilisegundos = 365L * 24 * 60 * 60 * 1000; 
        return new Date(fechaInicio.getTime() + unAnioEnMilisegundos);
    }

    public void agregarReserva(Reserva reserva) {
        if (reservas.size() < 5) { 
            reservas.add(reserva);
        } else {
            System.out.println("El bono ya tiene 5 reservas asociadas.");
        }
    }

    public boolean estaCaducado(Date fechaActual) {
        return fechaActual.after(fechaCaducidad);
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bono ID: ").append(idBono).append("\n");
        sb.append("Usuario ID: ").append(idUsuario).append("\n");
        sb.append("Fecha Primera Reserva: ").append(fechaPrimeraReserva).append("\n");
        sb.append("Fecha Caducidad: ").append(fechaCaducidad).append("\n");
        sb.append("Precio Reducido: ").append(precioReducido).append("\n");
        sb.append("Reservas Asociadas:\n");
        for (Reserva reserva : reservas) {
            sb.append(reserva.toString()).append("\n");
        }
        return sb.toString();
    }
}
