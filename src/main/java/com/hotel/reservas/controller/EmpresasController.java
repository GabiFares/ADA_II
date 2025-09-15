package com.hotel.reservas.controller;

import com.hotel.reservas.dto.EmpresaRequest;
import com.hotel.reservas.model.Empresa;
import com.hotel.reservas.model.Huesped;
import com.hotel.reservas.model.Persona;
import com.hotel.reservas.service.EmpresaService;
import com.hotel.reservas.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresasController {

    private final EmpresaService empresaService;
    private final PersonaService personaService;

    public EmpresasController(EmpresaService empresaService, PersonaService personaService) {
        this.empresaService = empresaService;
        this.personaService = personaService;
    }

    @GetMapping
    public List<Empresa> listar() {
        return empresaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtener(@PathVariable Long id) {
        return empresaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Empresa> getByRut(@PathVariable String rut) {
        return empresaService.obtenerPorRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    @PostMapping
    public Empresa crear(@RequestBody EmpresaRequest dto) {
        Persona persona = personaService.obtenerPorId(dto.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Empresa empresa = new Empresa();
        empresa.setRut(dto.getRut());
        empresa.setNomFantasia(dto.getNomFantasia());
        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setPersona(persona);

        return empresaService.guardar(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizar(@PathVariable Long id, @RequestBody EmpresaRequest dto) {
        Persona persona = personaService.obtenerPorId(dto.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Empresa empresa = new Empresa();
        empresa.setRut(dto.getRut());
        empresa.setNomFantasia(dto.getNomFantasia());
        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setPersona(persona);

        Empresa actualizada = empresaService.actualizar(id, empresa);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empresaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}