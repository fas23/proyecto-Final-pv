package ar.edu.unju.fi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.fi.model.Tripulante;

public interface ITripulanteDAO extends JpaRepository<Tripulante, Long> {
	
	public Optional<Tripulante>findByDocumento(String documento);
}
