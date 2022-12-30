package com.app.inventario.plato.application.queries.handlers;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.app.inventario.message.queries.GetAll;
import com.app.inventario.message.queries.GetHistoryByPlatoId;
import com.app.inventario.plato.application.queries.repositories.PlatoHistoryViewRepository;
import com.app.inventario.plato.application.queries.repositories.PlatoViewRepository;
import com.app.inventario.plato.application.queries.views.PlatoHistoryView;
import com.app.inventario.plato.application.queries.views.PlatoView;

@Component
public class GetPlatoHandler {
	private final PlatoViewRepository platoViewRepository;
    private final PlatoHistoryViewRepository platoHistoryViewRepository;

    public GetPlatoHandler(PlatoViewRepository platoViewRepository, PlatoHistoryViewRepository platoHistoryViewRepository) {
        this.platoViewRepository = platoViewRepository;
        this.platoHistoryViewRepository = platoHistoryViewRepository;
    }

    @QueryHandler
    public List<PlatoView> handle(GetAll query) {
        return this.platoViewRepository.findAll();
    }

    @QueryHandler
    public List<PlatoHistoryView> handle(GetHistoryByPlatoId query) {
        return this.platoHistoryViewRepository.getHistoryByPlatoId(query.getPlatoId());
    }
}
