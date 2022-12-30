package com.app.inventario.plato.application.queries.views;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "platos")
public class PlatoView {
	@Id @Column(length=36) @Getter @Setter
	private String id;
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
	
	public PlatoView(){
		
	}
	
	public PlatoView(String id, String nombre, String costoPlato, String codIngrediente, LocalDateTime createdAt) {
	    this.id = id;
	    this.nombre = nombre;
        this.costoPlato = costoPlato;
        this.codIngrediente = codIngrediente;
        this.createdAt = createdAt;
	 }

}
