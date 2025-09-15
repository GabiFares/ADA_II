package com.hotel.reservas.controller;

import com.hotel.reservas.dto.ResHabitacionRequest;
import com.hotel.reservas.model.Habitacion;
import com.hotel.reservas.model.ResHabitacion;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.service.HabitacionService;
import com.hotel.reservas.service.ResHabitacionService;
import com.hotel.reservas.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/res_habitaciones")
public class Res_habitacionesController {

    private final ResHabitacionService resHabitacionService;
    private final ReservaService reservaService;
    private final HabitacionService habitacionService;

    public Res_habitacionesController(ResHabitacionService resHabitacionService,
                                   ReservaService reservaService,
                                   HabitacionService habitacionService) {
        this.resHabitacionService = resHabitacionService;
        this.reservaService = reservaService;
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<ResHabitacion> listar() {
        return resHabitacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResHabitacion> obtener(@PathVariable Long id) {
        return resHabitacionService.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResHabitacion crear(@RequestBody ResHabitacionRequest dto) {
        Reserva reserva = reservaService.obtenerPorId(dto.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Habitacion habitacion = habitacionService.obtenerPorId(dto.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        ResHabitacion rh = new ResHabitacion();
        rh.setReserva(reserva);
        rh.setHabitacion(habitacion);

        return resHabitacionService.guardar(rh);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResHabitacion> actualizar(@PathVariable Long id, @RequestBody ResHabitacionRequest dto) {
        Reserva reserva = reservaService.obtenerPorId(dto.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Habitacion habitacion = habitacionService.obtenerPorId(dto.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        ResHabitacion rh = new ResHabitacion();
        rh.setReserva(reserva);
        rh.setHabitacion(habitacion);

        ResHabitacion actualizada = resHabitacionService.actualizar(id, rh);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resHabitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}