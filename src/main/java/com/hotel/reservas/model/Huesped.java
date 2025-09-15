package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "huespedes")
@Data
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(unique = true, nullable = false)
    private String ci;

    private String nombre;
    private String apellido;
}