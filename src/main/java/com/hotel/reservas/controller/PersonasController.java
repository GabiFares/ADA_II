package com.hotel.reservas.controller;

import com.hotel.reservas.dto.PersonaRequest;
import com.hotel.reservas.model.Direccion;
import com.hotel.reservas.model.Persona;
import com.hotel.reservas.service.DireccionService;
import com.hotel.reservas.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonasController {

    private final PersonaService personaService;
    private final DireccionService direccionService;

    public PersonasController(PersonaService personaService, DireccionService direccionService) {
        this.personaService = personaService;
        this.direccionService = direccionService;
    }

    @GetMapping
    public List<Persona> listar() {
        return personaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtener(@PathVariable Long id) {
        return personaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Persona crear(@RequestBody PersonaRequest dto) {
        Direccion direccion = direccionService.obtenerPorId(dto.getDireccionId())
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));

        Persona persona = new Persona();
        persona.setTelefono(dto.getTelefono());
        persona.setEmail(dto.getEmail());
        persona.setDireccion(direccion);

        return personaService.guardar(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizar(@PathVariable Long id, @RequestBody PersonaRequest dto) {
        Direccion direccion = direccionService.obtenerPorId(dto.getDireccionId())
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada"));

        Persona persona = new Persona();
        persona.setTelefono(dto.getTelefono());
        persona.setEmail(dto.getEmail());
        persona.setDireccion(direccion);

        Persona actualizada = personaService.actualizar(id, persona);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}