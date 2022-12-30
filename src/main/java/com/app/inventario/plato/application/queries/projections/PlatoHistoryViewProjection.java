package com.app.inventario.plato.application.queries.projections;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.app.inventario.message.events.PlatoEdited;
import com.app.inventario.message.events.PlatoRegistered;
import com.app.inventario.plato.application.queries.repositories.PlatoHistoryViewRepository;
import com.app.inventario.plato.application.queries.views.PlatoHistoryView;


@Component
public class PlatoHistoryViewProjection {
	private final PlatoHistoryViewRepository platoHistoryViewRepository;

    public PlatoHistoryViewProjection(PlatoHistoryViewRepository platoHistoryViewRepository) {
        this.platoHistoryViewRepository = platoHistoryViewRepository;
    }

    @EventHandler
    public void on(PlatoRegistered event) {
    	PlatoHistoryView view = new PlatoHistoryView(event.getId(), event.getNombre(), event.getCostoPlato(), event.getCodIngrediente(), event.getCreatedAt());
        platoHistoryViewRepository.save(view);
    }

    @EventHandler
    public void on(PlatoEdited event) {
        Optional<PlatoHistoryView> viewOptional = platoHistoryViewRepository.getLastByPlatoId(event.getId());
        if (viewOptional.isPresent()) {
            PlatoHistoryView view = viewOptional.get();
            view = new PlatoHistoryView(view);
            view.setNombre(event.getNombre());
            view.setCostoPlato(event.getCostoPlato());
            view.setCodIngrediente(event.getCodIngrediente());
            view.setCreatedAt(event.getUpdatedAt());
            platoHistoryViewRepository.save(view);
        }
    }
}
