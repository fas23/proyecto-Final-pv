package ar.edu.unju.fi.service;

import java.util.List;
//import java.util.Optional;

import ar.edu.unju.fi.model.Usuario;

public interface IUsuarioService {
	public void guardar(Usuario usuario);
	public Usuario modificar(Usuario unUsuario) throws Exception;
	public Usuario encontrarUsuario(Long id) throws Exception;
	public List<Usuario> listarTodos();
	public void eliminar(Long id);
	public Usuario buscarPorId(Long id);
	public Boolean buscarPorNombreApellido(String nombre, String apellido);
}
