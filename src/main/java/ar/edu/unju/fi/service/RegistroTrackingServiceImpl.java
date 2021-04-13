package ar.edu.unju.fi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.RegistroTracking;
import ar.edu.unju.fi.model.Tripulante;
import ar.edu.unju.fi.repository.IRegistroTrackingDAO;

@Service
public class RegistroTrackingServiceImpl implements IRegistroTrackingService {
	
	@Autowired
	private IRegistroTrackingDAO registroTrackingDAO;
	
	@Override
	public void guardar(RegistroTracking registroTracking) {
		registroTracking.setFechaHora(LocalDateTime.now()); 
		registroTrackingDAO.save(registroTracking);

	}

	@Override
	public List<RegistroTracking> listarTodos() {
		return registroTrackingDAO.findAll();
	}

	@Override
	public List<RegistroTracking> buscarTripulantes(String documento) {
		return registroTrackingDAO.findByTripulantesDocumento(documento);
	}

	@Override
	public List<RegistroTracking> buscarRegistroFechaHora(LocalDateTime fechaDesde, LocalDateTime fechaHasta, String nombreLocalidad) {
		return registroTrackingDAO.findByFechaHoraBetweenAndLocalidadNombre(fechaDesde, fechaHasta, nombreLocalidad);
	}

	@Override
	public RegistroTracking buscarPorId(Long id) throws Exception {
		return registroTrackingDAO.findById(id).orElseThrow(() -> new Exception("El Registro no Existe"));
	}
	
	@Override
	public List<Tripulante> obtenerTodosTripulantesVehiculo(RegistroTracking registroEncontrado){
		List<Tripulante> listatripulantes = new ArrayList<>();
		
		for(Tripulante tripulantes: registroEncontrado.getTripulantes()) {
			listatripulantes.add(tripulantes);
		}
		return listatripulantes;
	}

}
