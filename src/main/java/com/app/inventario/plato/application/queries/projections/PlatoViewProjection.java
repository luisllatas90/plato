package com.app.inventario.plato.application.queries.projections;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.app.inventario.message.events.PlatoEdited;
import com.app.inventario.message.events.PlatoRegistered;
import com.app.inventario.plato.application.queries.repositories.PlatoViewRepository;
import com.app.inventario.plato.application.queries.views.PlatoView;


@Component
public class PlatoViewProjection {
	private final PlatoViewRepository platoViewRepository;

    public PlatoViewProjection(PlatoViewRepository platoViewRepository) {
        this.platoViewRepository = platoViewRepository;
    }

    @EventHandler
    public void on(PlatoRegistered event) {
        PlatoView view = new PlatoView(event.getId(), event.getNombre(), event.getCostoPlato(), event.getCodIngrediente(), event.getCreatedAt());
    	platoViewRepository.save(view);
    }

    @EventHandler
    public void on(PlatoEdited event) {
        Optional<PlatoView> viewOptional = platoViewRepository.findById(event.getId());
        if (viewOptional.isPresent()) {
            PlatoView view = viewOptional.get();
            view.setNombre(event.getNombre());
            view.setCostoPlato(event.getCostoPlato());
            view.setCodIngrediente(event.getCodIngrediente());
            view.setUpdatedAt(event.getUpdatedAt());
            platoViewRepository.save(view);
        }
    }
}
