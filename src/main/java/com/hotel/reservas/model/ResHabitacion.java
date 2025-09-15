package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "res_habitaciones")
@Data
public class ResHabitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
}