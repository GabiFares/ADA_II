package com.hotel.reservas.controller;

import com.hotel.reservas.dto.SalaRequest;
import com.hotel.reservas.model.Estado;
import com.hotel.reservas.model.Sala;
import com.hotel.reservas.service.EstadoService;
import com.hotel.reservas.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalasController {

    private final SalaService salaService;
    private final EstadoService estadoService;

    public SalasController(SalaService salaService, EstadoService estadoService) {
        this.salaService = salaService;
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Sala> listar() {
        return salaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> obtener(@PathVariable Long id) {
        return salaService.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sala crear(@RequestBody SalaRequest dto) {
        Estado estado = estadoService.obtenerPorId(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Sala sala = new Sala();
        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        sala.setPrecio(dto.getPrecio());
        sala.setEquipamiento(dto.getEquipamiento());
        sala.setEstado(estado);

        return salaService.guardar(sala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> actualizar(@PathVariable Long id, @RequestBody SalaRequest dto) {
        Estado estado = estadoService.obtenerPorId(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Sala sala = new Sala();
        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        sala.setPrecio(dto.getPrecio());
        sala.setEquipamiento(dto.getEquipamiento());
        sala.setEstado(estado);

        Sala actualizada = salaService.actualizar(id, sala);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        salaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
