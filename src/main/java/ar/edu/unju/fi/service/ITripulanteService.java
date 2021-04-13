package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.model.Tripulante;

public interface ITripulanteService {
	public Tripulante buscarTripulanteDocumento(String documento) throws Exception;
	public void guardar(Tripulante tripulante);
	public List<Tripulante> buscarTodosTripulantes();
	public void guardarTripulanteEncontrado(Tripulante tripulante);
	public void borrarTodosTripulantes();
}
