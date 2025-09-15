package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PersonaRequest {

    @Schema(example = "099123456")
    private String telefono;

    @Schema(example = "gaby@example.com")
    private String email;

    @Schema(description = "ID de la dirección ya registrada")
    private Long direccionId;
}