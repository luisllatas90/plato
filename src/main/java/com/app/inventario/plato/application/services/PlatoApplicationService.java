package com.app.inventario.plato.application.services;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import com.app.inventario.message.commands.EditPlato;
import com.app.inventario.message.commands.RegisterPlato;
import com.app.inventario.plato.application.dto.request.EditPlatoRequest;
import com.app.inventario.plato.application.dto.request.RegisterPlatoRequest;
import com.app.inventario.plato.application.dto.response.EditPlatoResponse;
import com.app.inventario.plato.application.dto.response.RegisterPlatoResponse;
import com.app.inventario.plato.application.validators.EditPlatoValidator;
import com.app.inventario.plato.application.validators.RegisterPlatoValidator;
import com.app.inventario.shared.application.Notification;
import com.app.inventario.shared.application.Result;

@Component
public class PlatoApplicationService {
private final CommandGateway commandGateway;
private final RegisterPlatoValidator registerPlatoValidator;
private final EditPlatoValidator editPlatoValidator;
    
    public PlatoApplicationService(CommandGateway commandGateway, RegisterPlatoValidator registerPlatoValidator, EditPlatoValidator editPlatoValidator) {
        this.commandGateway = commandGateway;
        this.registerPlatoValidator = registerPlatoValidator;
        this.editPlatoValidator = editPlatoValidator;
    }
    
    public Result<RegisterPlatoResponse, Notification> register(RegisterPlatoRequest request) throws Exception {
        String idPlato =  UUID.randomUUID().toString();
        String createdBy = UUID.randomUUID().toString();
        RegisterPlato command = new RegisterPlato(
        		idPlato,
            request.getNombrePlato(),
            request.getCostoPlato(),
            request.getCodIngrediente(),
            createdBy
        );
        
        Notification notification = this.registerPlatoValidator.validate(command);
        if (notification.hasErrors()) return Result.failure(notification);
        commandGateway.sendAndWait(command);	
        	
        RegisterPlatoResponse registerPlatoResponse = new RegisterPlatoResponse(
            command.getId(),
            command.getNombre(),
            command.getCostoPlato(),
            command.getCodIngrediente()
        );
        return Result.success(registerPlatoResponse);
    }
    
    public Result<EditPlatoResponse, Notification> edit(EditPlatoRequest request) throws Exception {
        String updatedBy = UUID.randomUUID().toString();
        EditPlato command = new EditPlato(
            request.getIdPlato(),
            request.getNombrePlato(),
            request.getCostoPlato(),
            request.getCodIngrediente(),
            updatedBy
        );
        
        Notification notification = this.editPlatoValidator.validate(command);
        if (notification.hasErrors()) return Result.failure(notification);
        commandGateway.sendAndWait(command);
        EditPlatoResponse editPlatoResponse = new EditPlatoResponse(
            command.getId(),
            command.getNombre(),
            command.getCostoPlato(),
            command.getCodIngrediente()
        );
        return Result.success(editPlatoResponse);
    }
}
