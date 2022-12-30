package com.app.inventario.plato.application.queries.views;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "platos_history")
public class PlatoHistoryView {
	@Id @GeneratedValue @Getter @Setter
	 private Long id;
	 @Column(length=36) @Getter @Setter
	 private String platoId;
	 @Column(length=100) @Getter @Setter
	 private String nombre;
	 @Column(length=10) @Getter @Setter
	 private String costoPlato;
	 @Column(length=36) @Getter @Setter
     private String codIngrediente;
	 @Column() @Getter @Setter
	 private LocalDateTime createdAt;
	 @Column(nullable = true) @Getter @Setter
	 private LocalDateTime updatedAt;

	 public PlatoHistoryView() {
	 }

	 public PlatoHistoryView(String platoId, String nombre, String costoPlato, String codIngrediente,LocalDateTime createdAt) {
	    this.platoId = platoId;
	    this.nombre = nombre;
        this.costoPlato = costoPlato;
        this.codIngrediente = codIngrediente;
        this.createdAt = createdAt;
	 }
	    
	 public PlatoHistoryView(PlatoHistoryView view) {
	   	this.platoId = view.getPlatoId();
	  	this.nombre = view.getNombre();
	    this.costoPlato = view.getCostoPlato();
	    this.codIngrediente = view.getCodIngrediente();
	    this.createdAt = view.getCreatedAt();        
	 }
}
