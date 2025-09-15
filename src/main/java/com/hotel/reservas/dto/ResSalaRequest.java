package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResSalaRequest {
    @Schema(example = "1")
    private Long reservaId;

    @Schema(example = "2")
    private Long salaId;
}