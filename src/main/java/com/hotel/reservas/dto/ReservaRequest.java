package com.hotel.reservas.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class ReservaRequest {

    @Schema(description = "ID del usuario que realiza la reserva", required = true)
    private Long usuarioId;

    @Schema(description = "ID del huésped (opcional)")
    private Long huespedId;

    @Schema(description = "ID de la empresa (opcional)")
    private Long empresaId;

    @Schema(description = "Fecha y hora de ingreso", example = "2025-06-01T14:00:00")
    private String fechaIngreso;

    @Schema(description = "Fecha y hora de salida", example = "2025-08-01T14:10:00")
    private String fechaSalida;
}