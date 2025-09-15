package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "personas")
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telefono;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;
}