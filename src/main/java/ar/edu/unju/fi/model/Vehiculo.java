package ar.edu.unju.fi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String patente;
	private String color;
	private String titular;
	private String marca;
	private String modelo;
	private String tipo;
	private String numeroChasis;
	private String numeroMotor;
	
	public Vehiculo() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumeroChasis() {
		return numeroChasis;
	}

	public void setNumeroChasis(String numeroChasis) {
		this.numeroChasis = numeroChasis;
	}

	public String getNumeroMotor() {
		return numeroMotor;
	}

	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", patente=" + patente + ", color=" + color + ", titular=" + titular + ", marca="
				+ marca + ", modelo=" + modelo + ", tipo=" + tipo + ", numeroChasis=" + numeroChasis + ", numeroMotor="
				+ numeroMotor + "]";
	}
	
	

}
