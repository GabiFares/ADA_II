package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "salas")
@Data
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int capacidad;
    private int precio;
    private String equipamiento;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
}
