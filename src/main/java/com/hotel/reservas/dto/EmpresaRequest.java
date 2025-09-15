package com.hotel.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmpresaRequest {

    @Schema(example = "217654320012")
    private String rut;

    @Schema(example = "TechSoluciones")
    private String nomFantasia;

    @Schema(example = "Tech Soluciones S.A.")
    private String razonSocial;

    @Schema(example = "3", description = "ID de la persona asociada")
    private Long personaId;
}