package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Localidad;

public interface ILocalidadService {
	public void guardar(Localidad localidad);
	public List<Localidad> listarTodos();
	public void eliminar(Long id);
	public Localidad buscarPorId(Long id);
	public Boolean buscarPorNombre(String nombre);
}
