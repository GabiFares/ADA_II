package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "res_salas")
@Data
public class ResSala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
}