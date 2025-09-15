package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OcupacionRequest {
    @Schema(example = "1")
    private Long reservaId;

    @Schema(example = "4")
    private Long habitacionId;

    @Schema(example = "3")
    private Long huespedId;

    @Schema(example = "2025-06-01T14:00:00")
    private String fechaIngreso;

    @Schema(example = "2025-06-05T12:00:00")
    private String fechaFin;
}