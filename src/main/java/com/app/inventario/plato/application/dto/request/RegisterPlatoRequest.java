package com.app.inventario.plato.application.dto.request;

import lombok.Value;

@Value
public class RegisterPlatoRequest {
    private String nombrePlato;
    private String costoPlato;
    private String codIngrediente;
}
