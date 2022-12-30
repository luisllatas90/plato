package com.app.inventario.plato.application.queries.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.inventario.plato.application.queries.views.PlatoHistoryView;

@Repository
public interface PlatoHistoryViewRepository extends JpaRepository<PlatoHistoryView, String> {
	@Query(value = "SELECT * FROM platos_history WHERE plato_id = (SELECT MAX(plato_id) FROM platos_history WHERE plato_id = :platoId)", nativeQuery = true)
	 Optional<PlatoHistoryView> getLastByPlatoId(String platoId);

	@Query(value = "SELECT * FROM platos_history WHERE plato_id = :platoId ORDER BY created_at", nativeQuery = true)
	 List<PlatoHistoryView> getHistoryByPlatoId(String platoId);
}
