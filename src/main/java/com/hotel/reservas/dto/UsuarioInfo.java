package com.hotel.reservas.dto;

import lombok.Data;

@Data
public class UsuarioInfo {
    private Long id;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String rol;
}
