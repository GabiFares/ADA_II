package com.hotel.reservas.controller;

import com.hotel.reservas.model.Estado;
import com.hotel.reservas.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadosController {
    private final EstadoService service;

    public EstadosController(EstadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Estado> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estado crear(@RequestBody Estado r) { return service.guardar(r); }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualizar(@PathVariable Long id, @RequestBody Estado r) {
        Estado actualizado = service.actualizar(id, r);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}