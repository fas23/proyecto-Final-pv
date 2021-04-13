package ar.edu.unju.fi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Localidad;
import ar.edu.unju.fi.repository.ILocalidadDAO;

@Service
public class LocalidadServiceImpl implements ILocalidadService {
	
	@Autowired
	private ILocalidadDAO localidadDAO;

	@Override
	public List<Localidad> listarTodos() {
		return localidadDAO.findAll();
	}

	@Override
	public void guardar(Localidad localidad) {
		localidadDAO.save(localidad);
		
	}

	@Override
	public void eliminar(Long id) {
		localidadDAO.deleteById(id);
		
	}

	@Override
	public Localidad buscarPorId(Long id) {
		return localidadDAO.findById(id).orElse(null);
	}

	@Override
	public Boolean buscarPorNombre(String nombre) {
		
		Boolean encontrado= false;
		Optional<Localidad> consultado = localidadDAO.findByNombre(nombre);
		
		if(consultado.isPresent()) {
			encontrado = true;
		}
		
		return encontrado;
	}
	

}
