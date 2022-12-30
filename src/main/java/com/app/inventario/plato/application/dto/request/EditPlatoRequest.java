package com.app.inventario.plato.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class EditPlatoRequest {
	private @Setter @Getter String idPlato;
    private @Getter String nombrePlato;
    private @Getter String costoPlato;
    private @Getter String codIngrediente;

}
