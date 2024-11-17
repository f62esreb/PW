package business;


public class ReservasDTO {
/**
 * Este es el ReservasDTO
 */
	private int id_reserva_;
	private int id_pistas_;
	private int id_user_;
	private int duracion_;
	private java.sql.Date fecha_reserva_;
	private double precio_;
	private double descuento_;
	private String tipo_reserva_;
	private int id_bono_;

	/**
	 * Este es el Constructor ReservasDTO 
	 */
	public ReservasDTO(String id_pistas, String id_user, int duracion, java.sql.Date fecha_reserva, java.sql.Time hora_reserva, double precio, double descuento,
						String tipo_reserva, int id_bono) {
		this.id_user_=id_user;
		this.duracion_=duracion;
		this.fecha_reserva_=fecha_reserva;
		this.hora_reserva_=hora_reserva;
		this.precio_=precio;
		this.descuento_=descuento;
		this.tipo_reserva_=tipo_reserva;
		this.id_bono_=id_bono;
	}
	
	/**
	 * Este es el Get/Set de las variables de ReservasDTO
	 */
	public int getId_reserva_() {
	return id_reserva_;
}

	public void setId_reserva_(int id_reserva_) {
		this.id_reserva_ = id_reserva_;
	}


	public String getId_pistas_() {
		return id_pistas_;
	}

	public void setId_pistas_(String id_pistas_) {
		this.id_pistas_ = id_pistas_;
	}

	public String getId_user_() {
		return id_user_;
	}

	public void setId_user_(String id_user_) {
		this.id_user_ = id_user_;
	}

	public int getDuracion_() {
		return duracion_;
	}

	public void setDuracion_(int duracion_) {
		this.duracion_ = duracion_;
	}

	public java.sql.Date getFecha_reserva_() {
		return fecha_reserva_;
	}

	public void setFecha_reserva_(java.sql.Date fecha_reserva_) {
		this.fecha_reserva_ = fecha_reserva_;
	}

	public java.sql.Time getHora_reserva_() {
		return hora_reserva_;
	}

	public void setHora_reserva_(java.sql.Time hora_reserva_) {
		this.hora_reserva_ = hora_reserva_;
	}

	public double getPrecio_() {
		return precio_;
	}

	public void setPrecio_(double precio_) {
		this.precio_ = precio_;
	}

	public double getDescuento_() {
		return descuento_;
	}

	public void setDescuento_(double descuento_) {
		this.descuento_ = descuento_;
	}
	
	public String getTipo_reserva_() {
		return tipo_reserva_;
	}

	public void setTipo_reserva_(String tipo_reserva_) {
		this.tipo_reserva_ = tipo_reserva_;
	}

	public int getId_bono_() {
		return id_bono_;
	}

	public void setId_bono_(int id_bono_) {
		this.id_bono_ = id_bono_;
	}



}