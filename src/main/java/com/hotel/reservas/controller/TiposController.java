package com.hotel.reservas.controller;

import com.hotel.reservas.model.Tipo;
import com.hotel.reservas.service.TipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos")
public class TiposController {
    private final TipoService service;

    public TiposController(TipoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tipo> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tipo crear(@RequestBody Tipo r) { return service.guardar(r); }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> actualizar(@PathVariable Long id, @RequestBody Tipo r) {
        Tipo actualizado = service.actualizar(id, r);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}