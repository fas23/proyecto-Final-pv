package ar.edu.unju.fi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.edu.unju.fi.model.RegistroTracking;

public interface IRegistroTrackingDAO extends JpaRepository<RegistroTracking, Long> {
	
	//se aplica un ordenamiento en forma descendente de mayor a menor
	@Query("from RegistroTracking r order by r.fechaHora desc")
	public List<RegistroTracking> findByTripulantesDocumento(String documento);
	
	public List<RegistroTracking> findByFechaHoraBetweenAndLocalidadNombre(LocalDateTime fechaDesde, LocalDateTime fechaHasta, String nombreLocalidad);

}
