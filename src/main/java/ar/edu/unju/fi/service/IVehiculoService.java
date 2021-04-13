package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Vehiculo;

public interface IVehiculoService {
	
	public Vehiculo buscarVehiculo(String patente);
	public void guardar(Vehiculo vehiculo);
}
