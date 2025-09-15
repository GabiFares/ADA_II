package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocupaciones")
@Data
public class Ocupacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "huesped_id")
    private Huesped huesped;

    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaFin;
}