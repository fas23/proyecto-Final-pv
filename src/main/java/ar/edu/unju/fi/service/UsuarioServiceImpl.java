package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.repository.IUsuarioDAO;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	public void guardar(Usuario usuario) {
		String pass = usuario.getPassword();
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		usuario.setPassword(passEncoder.encode(pass));
		usuarioDAO.save(usuario);

	}

	@Override
	public List<Usuario> listarTodos() {
		return usuarioDAO.findAll();
	}

	@Override
	public void eliminar(Long id) {
		usuarioDAO.deleteById(id);

	}

	@Override
	public Usuario buscarPorId(Long id) {
		
		return usuarioDAO.findById(id).orElse(null);
	}

	@Override
	public Boolean buscarPorNombreApellido(String nombre, String apellido) {
		Boolean encontrado = false;
		
		Optional<Usuario> consultado = usuarioDAO.findByNombreRealAndApellidoReal(nombre, apellido); 
		if(consultado.isPresent()) {
			encontrado = true;
		}
		return encontrado;
		
	}

	@Override
	public Usuario modificar(Usuario unUsuario) throws Exception {
		Usuario usuarioGuardar = encontrarUsuario(unUsuario.getId());
		mapearUsuario(unUsuario,usuarioGuardar);
		return usuarioDAO.save(usuarioGuardar);
	}

	//se cambia solo aquellos campos susceptibles de cambio
	private void mapearUsuario(Usuario desde, Usuario hacia) {
		hacia.setNombreReal(desde.getNombreReal());
		hacia.setNombreReal(desde.getNombreReal());
		hacia.setNombreUsuario(desde.getNombreUsuario());
		hacia.setTipoUsuario(desde.getTipoUsuario());
		
	}

	@Override
	public Usuario encontrarUsuario(Long id) throws Exception {
		//devuelve un opcional
		return usuarioDAO.findById(id).orElseThrow(()-> new Exception("El usuario no existe"));
	}

}
