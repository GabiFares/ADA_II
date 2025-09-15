package com.hotel.reservas.controller;

import com.hotel.reservas.model.Direccion;
import com.hotel.reservas.service.DireccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionesController {

    private final DireccionService service;

    public DireccionesController(DireccionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Direccion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Direccion crear(@RequestBody Direccion d) {
        return service.guardar(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizar(@PathVariable Long id, @RequestBody Direccion nueva) {
        Direccion actualizada = service.actualizar(id, nueva);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}