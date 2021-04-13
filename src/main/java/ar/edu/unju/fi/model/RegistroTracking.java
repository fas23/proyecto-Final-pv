package ar.edu.unju.fi.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "registros_tracking")
public class RegistroTracking implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime fechaHora;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehiculo_id")
	private Vehiculo vehiculo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "regis_tracking_tripulantes", 
			   joinColumns = @JoinColumn(name = "registro_tracking_id"),
			   inverseJoinColumns =  @JoinColumn(name = "tripulante_id"))
	private List<Tripulante> tripulantes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "localidad_id")
	private Localidad localidad;
	
	private String detalleLugarRegistro;
	
	public RegistroTracking() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<Tripulante> getTripulantes() {
		return tripulantes;
	}

	public void setTripulantes(List<Tripulante> tripulantes) {
		this.tripulantes = tripulantes;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getDetalleLugarRegistro() {
		return detalleLugarRegistro;
	}

	public void setDetalleLugarRegistro(String detalleLugarRegistro) {
		this.detalleLugarRegistro = detalleLugarRegistro;
	}

	@Override
	public String toString() {
		return "RegistroTracking [id=" + id + ", fechaHora=" + fechaHora + ", vehiculo=" + vehiculo + ", tripulantes="
				+ tripulantes + ", localidad=" + localidad + ", detalleLugarRegistro=" + detalleLugarRegistro + "]";
	}
	
	

}
