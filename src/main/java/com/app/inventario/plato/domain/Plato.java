package com.app.inventario.plato.domain;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.time.LocalDateTime;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import com.app.inventario.message.commands.EditPlato;
import com.app.inventario.message.commands.RegisterPlato;
import com.app.inventario.message.events.PlatoEdited;
import com.app.inventario.message.events.PlatoRegistered;
import com.app.inventario.shared.domain.CodIngrediente;
import com.app.inventario.shared.domain.CostoPlato;
import com.app.inventario.shared.domain.NombrePlato;

@Aggregate
public class Plato {
	@AggregateIdentifier
     private String id;
     private NombrePlato nombre;
     private CostoPlato costoPlato;
     private CodIngrediente codIngrediente; 
     
     public Plato() {
     }

     @CommandHandler
     public Plato(RegisterPlato command) {
         LocalDateTime createdAt = LocalDateTime.now();
          PlatoRegistered event = new PlatoRegistered(
             command.getId(),
             command.getNombre(),
             command.getCostoPlato(),
             command.getCodIngrediente(),
             createdAt,
             command.getCreatedBy()
         );
         apply(event);
     }

     @CommandHandler
     public void handle(EditPlato command) {
         LocalDateTime updatedAt = LocalDateTime.now();
         PlatoEdited event = new PlatoEdited(
        		 command.getId(),
                 command.getNombre(),
                 command.getCostoPlato(),
                 command.getCodIngrediente(),
            updatedAt,
            command.getUpdatedBy()
         );
         apply(event);
     }

     @EventSourcingHandler
     protected void on(PlatoRegistered event) {
         id = event.getId();
         nombre = NombrePlato.create(event.getNombre()).getSuccess();
         costoPlato = CostoPlato.create(event.getCostoPlato()).getSuccess();
         codIngrediente = CodIngrediente.create(event.getCodIngrediente()).getSuccess();
     }

     @EventSourcingHandler
     protected void on(PlatoEdited event) {
    	 nombre = NombrePlato.create(event.getNombre()).getSuccess();
         costoPlato = CostoPlato.create(event.getCostoPlato()).getSuccess();
         codIngrediente = CodIngrediente.create(event.getCodIngrediente()).getSuccess();
     }
}
