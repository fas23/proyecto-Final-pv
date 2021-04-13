package ar.edu.unju.fi.service;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unju.fi.model.RegistroTracking;
import ar.edu.unju.fi.model.Tripulante;


public interface IRegistroTrackingService {
	public void guardar(RegistroTracking registroTracking);
	public List<RegistroTracking> listarTodos(); 
	public List<RegistroTracking> buscarTripulantes(String documento);
	public List<RegistroTracking> buscarRegistroFechaHora(LocalDateTime fechaDesde,LocalDateTime fechaHasta, String nombreLocalidad);
	public RegistroTracking buscarPorId(Long id) throws Exception; 
	public List<Tripulante> obtenerTodosTripulantesVehiculo(RegistroTracking registroEncontrado);
	
}	
