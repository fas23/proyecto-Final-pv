package ar.edu.unju.fi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Vehiculo;
import ar.edu.unju.fi.repository.IVehiculoDAO;

@Service
public class VehiculoService implements IVehiculoService {
	
	@Autowired
	IVehiculoDAO vehiculoDAO;
	
	@Override
	public Vehiculo buscarVehiculo(String patente) {
		return  vehiculoDAO.findByPatente(patente);
	}

	@Override
	public void guardar(Vehiculo vehiculo) {
		vehiculoDAO.save(vehiculo);
		
	}

}
