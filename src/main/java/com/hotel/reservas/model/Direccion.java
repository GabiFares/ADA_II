package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "direcciones")
@Data
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pais;
    private String departamento;
    private String ciudad;
    private String calle;
    private String numPuerta;
}