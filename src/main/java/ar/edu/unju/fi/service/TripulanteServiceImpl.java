package ar.edu.unju.fi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Tripulante;
import ar.edu.unju.fi.repository.ITripulanteDAO;

@Service
public class TripulanteServiceImpl implements ITripulanteService {

	@Autowired
	private ITripulanteDAO tripulanteDAO; 
	
	private List<Tripulante> listadoAuxiliar = new ArrayList<>();
	
	@Override
	public Tripulante buscarTripulanteDocumento(String documento) throws Exception{
		
		return tripulanteDAO.findByDocumento(documento).orElseThrow(() -> new Exception("El Tripulante no Existe"));
	}

	@Override
	public void guardar(Tripulante tripulante) {
		tripulanteDAO.save(tripulante);
		listadoAuxiliar.add(tripulante);
		
	}

	@Override
	public List<Tripulante> buscarTodosTripulantes() {
		
		return listadoAuxiliar;
	}

	/**
	 * metodo que guarda el tripulante en una lista auxiliar
	 */
	@Override
	public void guardarTripulanteEncontrado(Tripulante tripulante) {
		listadoAuxiliar.add(tripulante);
		
	}

	/**
	 * metodo que borra la lista auxiliar
	 */
	@Override
	public void borrarTodosTripulantes() {
		//borro el listado auxiliar
		listadoAuxiliar = new ArrayList<>();
		
	}
	

}
