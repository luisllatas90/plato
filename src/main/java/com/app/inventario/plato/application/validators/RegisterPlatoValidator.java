package com.app.inventario.plato.application.validators;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.app.inventario.message.commands.RegisterIngrediente;
import com.app.inventario.message.commands.RegisterPlato;
import com.app.inventario.shared.application.Notification;

@Component
public class RegisterPlatoValidator {

	public RegisterPlatoValidator() {
	}
	
	public Notification validate(RegisterPlato command)
    {
        Notification notification = new Notification();
        String nombre = command.getNombre();
        if (nombre == null) {
            notification.addError("Nombre de plato es requerido");
        }
        String costoPlato = command.getCostoPlato();
        if (costoPlato == null) {
            notification.addError("Costo plato es requerido");
        }
        String codIngrediente = command.getCodIngrediente();
        if (codIngrediente == null) {
            notification.addError("Código de ingrediente es requerido");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        
        return notification;
    }
}
