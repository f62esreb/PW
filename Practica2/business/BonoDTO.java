package business;

import java.sql.Date;

public class BonoDTO {
	
	private int id;
	private int descuento;
	private Date FechaInicio;
	private Date FechaCaducidad;
	

//Id	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
//Descuento
	
	public int getDescuento() {
		return this.descuento;
	}
	
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

//Numero de sesiones
	
	public int getFechaInicio() {
		return this.FechaInicio;
	}
	
	public void setFechaInicio(int FechaInicio) {
		this.FechaInicio = FechaInicio;
	}

//Fecha de Caducidad
	
	public Date getFechaCaducidad() {
		return this.FechaCaducidad;
	}
	
	public void setFechaCadudcidad(Date fecha_cadudcidad) {
		this.FechaCaducidad = fecha_cadudcidad;
	}

}
