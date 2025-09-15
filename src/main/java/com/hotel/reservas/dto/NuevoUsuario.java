package com.hotel.reservas.dto;

import lombok.Data;

@Data
public class NuevoUsuario {
    private String ci;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenia;
    private Long idRol;
    private Long idPersona;
}
