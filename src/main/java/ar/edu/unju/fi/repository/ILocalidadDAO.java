package ar.edu.unju.fi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unju.fi.model.Localidad;

public interface ILocalidadDAO extends JpaRepository<Localidad, Long>{
	public Optional<Localidad> findByNombre(String nombre);
}
