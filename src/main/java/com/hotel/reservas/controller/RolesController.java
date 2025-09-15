package com.hotel.reservas.controller;

import com.hotel.reservas.model.Rol;
import com.hotel.reservas.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    private final RolService service;

    public RolesController(RolService service) {
        this.service = service;
    }

    @GetMapping
    public List<Rol> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Rol crear(@RequestBody Rol r) { return service.guardar(r); }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @RequestBody Rol r) {
        Rol actualizado = service.actualizar(id, r);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}