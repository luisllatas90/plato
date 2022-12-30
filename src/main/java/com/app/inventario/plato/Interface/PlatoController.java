package com.app.inventario.plato.Interface;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.inventario.message.queries.GetAll;
import com.app.inventario.message.queries.GetHistoryByPlatoId;
import com.app.inventario.plato.application.dto.request.EditPlatoRequest;
import com.app.inventario.plato.application.dto.request.RegisterPlatoRequest;
import com.app.inventario.plato.application.dto.response.EditPlatoResponse;
import com.app.inventario.plato.application.dto.response.RegisterPlatoResponse;
import com.app.inventario.plato.application.queries.views.PlatoHistoryView;
import com.app.inventario.plato.application.queries.views.PlatoView;
import com.app.inventario.plato.application.services.PlatoApplicationService;
import com.app.inventario.shared.Interface.ApiController;
import com.app.inventario.shared.application.Notification;
import com.app.inventario.shared.application.Result;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/platos")
@Tag(name = "Platos")
public class PlatoController {
	private final PlatoApplicationService platoApplicationService;
    private final QueryGateway queryGateway; 
    
    public PlatoController(PlatoApplicationService platoApplicationService, QueryGateway queryGateway) {
        this.platoApplicationService = platoApplicationService;
        this.queryGateway = queryGateway;
    }
    
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterPlatoRequest request) {
        try {
            Result<RegisterPlatoResponse, Notification> result = platoApplicationService.register(request);
            if (result.isSuccess()) return ApiController.created(result.getSuccess());
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }

    @PutMapping("/{platoId}")
    public ResponseEntity<Object> editPlato(@PathVariable("platoId") String idPlato, @RequestBody EditPlatoRequest request) {
        try {
            request.setIdPlato(idPlato);
            Result<EditPlatoResponse, Notification> result = platoApplicationService.edit(request);
            if (result.isSuccess()) return ApiController.ok(result.getSuccess());
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }

    @GetMapping("")
    @Operation(summary = "Obtener todos los platos")
    public ResponseEntity<Object> getAllPlatos() {
        try {
            CompletableFuture<List<PlatoView>> future = queryGateway.query(new GetAll(), ResponseTypes.multipleInstancesOf(PlatoView.class));
            List<PlatoView> platos = future.get();
            return ApiController.ok(platos);
        } catch( Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }

    @GetMapping("/{platoId}/history")
    @Operation(summary = "Obtener histórico de platos")
    public ResponseEntity<Object> getHistoryByPlatoId(@PathVariable("platoId") String idPlato) {
        try {
            CompletableFuture<List<PlatoHistoryView>> future = queryGateway.query(new GetHistoryByPlatoId(idPlato), ResponseTypes.multipleInstancesOf(PlatoHistoryView.class));
            List<PlatoHistoryView> platos = future.get();
            return ApiController.ok(platos);
        } catch( Exception e) {
            e.printStackTrace();
            return ApiController.serverError();
        }
    }
    
}
