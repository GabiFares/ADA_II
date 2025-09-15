package com.hotel.reservas.dto;

import lombok.Data;

@Data
public class RegistroRequest {
    // Usuario
    private String ci;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenia;
    // Persona
    private String telefono;
    private String email;
    // Dirección
    private String pais;
    private String departamento;
    private String ciudad;
    private String calle;
    private String numPuerta;
    private Long idRol;
} 