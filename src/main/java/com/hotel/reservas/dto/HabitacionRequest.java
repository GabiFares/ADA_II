package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class HabitacionRequest {
    @Schema(example = "true")
    private boolean accesible;

    @Schema(example = "true")
    private boolean disponible;

    @Schema(example = "1")
    private Long categoriaId;

    @Schema(example = "3500")
    private int precio;

    @Schema(example = "205")
    private int numHabitacion;

    @Schema(example = "Con vista al mar")
    private String observaciones;

    @Schema(example = "2")
    private Long tipoId;

    @Schema(example = "3")
    private Long estadoId;
}