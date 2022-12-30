package com.app.inventario.plato.infrastructure.config;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.inventario.plato.domain.Plato;
import com.thoughtworks.xstream.XStream;

@Configuration
public class AxonConfig {	
	@Bean
	public Repository<Plato> eventSourcingRepositoryPlato(EventStore eventStore) {
	        return EventSourcingRepository.builder(Plato.class)
	            .eventStore(eventStore)
	            .build();
	}
	
	@Bean
    public XStream xStream() {
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {
            "com.app.inventario.**"
        });
        return xStream;
    }
}
