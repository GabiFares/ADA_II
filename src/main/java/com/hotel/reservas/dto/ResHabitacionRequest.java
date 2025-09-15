package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResHabitacionRequest {
    @Schema(example = "1")
    private Long reservaId;

    @Schema(example = "5")
    private Long habitacionId;
}