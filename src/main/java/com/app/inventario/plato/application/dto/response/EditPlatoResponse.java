package com.app.inventario.plato.application.dto.response;

import java.util.Date;

import lombok.Value;

@Value
public class EditPlatoResponse {
	private String idPlato;
    private String nombrePlato;
    private String costoPlato;
    private String codIngrediente;
}
