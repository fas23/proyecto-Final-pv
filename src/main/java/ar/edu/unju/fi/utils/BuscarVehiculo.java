package ar.edu.unju.fi.utils;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Localidad;

@Component
public class BuscarVehiculo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Localidad localidad;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaDesde;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaHasta;

	public BuscarVehiculo() {
		// TODO Auto-generated constructor stub
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public LocalDateTime getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(LocalDateTime fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public LocalDateTime getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDateTime fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
