package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioRequest {

    @Schema(example = "54752170")
    private String ci;

    @Schema(example = "Juan")
    private String nombre;

    @Schema(example = "Perez")
    private String apellido;

    @Schema(description = "ID de persona asociada")
    private Long personaId;

    @Schema(description = "ID del rol")
    private Long rolId;
}
