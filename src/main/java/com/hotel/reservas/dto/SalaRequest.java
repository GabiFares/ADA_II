package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SalaRequest {
    @Schema(example = "Sala de Conferencias A")
    private String nombre;

    @Schema(example = "50")
    private int capacidad;

    @Schema(example = "1500")
    private int precio;

    @Schema(example = "Proyector, Sillas, Sonido")
    private String equipamiento;

    @Schema(example = "1")
    private Long estadoId;
}
