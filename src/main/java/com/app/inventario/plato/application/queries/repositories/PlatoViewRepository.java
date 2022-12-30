package com.app.inventario.plato.application.queries.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.inventario.plato.application.queries.views.PlatoView;

@Repository
public interface PlatoViewRepository extends JpaRepository<PlatoView, String>{
	Optional<PlatoView> getByNombre(String nombre);

	@Query(value = "SELECT * FROM platos WHERE id <> :id AND nombre = :nombre", nativeQuery = true)
	Optional<PlatoView> getByIdAndNombre(String id, String nombre);
}
