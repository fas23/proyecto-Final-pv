package ar.edu.unju.fi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name ="usuarios")
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Campo obligatorio")
	private String nombreUsuario;
	
	@NotEmpty(message = "Campo obligatorio")
	@Size(min = 8, message = "Password debe contener al menos 8 caracteres")
	private String password;
	
	@NotEmpty(message = "Campo obligatorio")
	private String nombreReal;
	
	@NotEmpty(message = "Campo obligatorio")
	private String apellidoReal;
	
	private String tipoUsuario;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombreReal() {
		return nombreReal;
	}
	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}
	public String getApellidoReal() {
		return apellidoReal;
	}
	public void setApellidoReal(String apellidoReal) {
		this.apellidoReal = apellidoReal;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", nombreReal="
				+ nombreReal + ", apellidoReal=" + apellidoReal + ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
	
}
