package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class HuespedRequest {

    @Schema(example = "49876543")
    private String ci;

    @Schema(example = "Juan")
    private String nombre;

    @Schema(example = "Perez")
    private String apellido;

    @Schema(description = "ID de la persona")
    private Long personaId;
}
