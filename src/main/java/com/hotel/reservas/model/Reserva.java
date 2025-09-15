package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "huesped_id")
    private Huesped huesped;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
}