package ar.edu.unju.fi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.unju.fi.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByNombreRealAndApellidoReal(String nombre, String apellido);
	
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
