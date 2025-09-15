package com.hotel.reservas.controller;

import com.hotel.reservas.dto.OcupacionRequest;
import com.hotel.reservas.model.Habitacion;
import com.hotel.reservas.model.Huesped;
import com.hotel.reservas.model.Ocupacion;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.service.HabitacionService;
import com.hotel.reservas.service.HuespedService;
import com.hotel.reservas.service.OcupacionService;
import com.hotel.reservas.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ocupaciones")
public class OcupacionesController {

    private final OcupacionService ocupacionService;
    private final ReservaService reservaService;
    private final HabitacionService habitacionService;
    private final HuespedService huespedService;

    public OcupacionesController(OcupacionService ocupacionService,
                               ReservaService reservaService,
                               HabitacionService habitacionService,
                               HuespedService huespedService) {
        this.ocupacionService = ocupacionService;
        this.reservaService = reservaService;
        this.habitacionService = habitacionService;
        this.huespedService = huespedService;
    }

    @GetMapping
    public List<Ocupacion> listar() {
        return ocupacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocupacion> obtener(@PathVariable Long id) {
        return ocupacionService.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ocupacion crear(@RequestBody OcupacionRequest dto) {
        Reserva reserva = reservaService.obtenerPorId(dto.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Habitacion habitacion = habitacionService.obtenerPorId(dto.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        Huesped huesped = huespedService.obtenerPorId(dto.getHuespedId())
                .orElseThrow(() -> new RuntimeException("Huésped no encontrado"));

        Ocupacion o = new Ocupacion();
        o.setReserva(reserva);
        o.setHabitacion(habitacion);
        o.setHuesped(huesped);
        o.setFechaIngreso(LocalDateTime.parse(dto.getFechaIngreso()));
        o.setFechaFin(LocalDateTime.parse(dto.getFechaFin()));

        return ocupacionService.guardar(o);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocupacion> actualizar(@PathVariable Long id, @RequestBody OcupacionRequest dto) {
        Reserva reserva = reservaService.obtenerPorId(dto.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Habitacion habitacion = habitacionService.obtenerPorId(dto.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        Huesped huesped = huespedService.obtenerPorId(dto.getHuespedId())
                .orElseThrow(() -> new RuntimeException("Huésped no encontrado"));

        Ocupacion o = new Ocupacion();
        o.setReserva(reserva);
        o.setHabitacion(habitacion);
        o.setHuesped(huesped);
        o.setFechaIngreso(LocalDateTime.parse(dto.getFechaIngreso()));
        o.setFechaFin(LocalDateTime.parse(dto.getFechaFin()));

        Ocupacion actualizada = ocupacionService.actualizar(id, o);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ocupacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
