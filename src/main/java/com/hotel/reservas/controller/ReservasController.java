package com.hotel.reservas.controller;

import com.hotel.reservas.dto.ReservaRequest;
import com.hotel.reservas.model.Empresa;
import com.hotel.reservas.model.Huesped;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.model.Usuario;
import com.hotel.reservas.service.EmpresaService;
import com.hotel.reservas.service.HuespedService;
import com.hotel.reservas.service.ReservaService;
import com.hotel.reservas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservasController {

    private final ReservaService reservaService;
    private final UsuarioService usuarioService;
    private final HuespedService huespedService;
    private final EmpresaService empresaService;

    public ReservasController(ReservaService reservaService, UsuarioService usuarioService,
                             HuespedService huespedService, EmpresaService empresaService) {
        this.reservaService = reservaService;
        this.usuarioService = usuarioService;
        this.huespedService = huespedService;
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtener(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reserva crear(@RequestBody ReservaRequest dto) {
        Usuario usuario = usuarioService.obtenerPorId(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Huesped huesped = (dto.getHuespedId() != null)
                ? huespedService.obtenerPorId(dto.getHuespedId())
                .orElseThrow(() -> new RuntimeException("Huésped no encontrado"))
                : null;

        Empresa empresa = (dto.getEmpresaId() != null)
                ? empresaService.obtenerPorId(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"))
                : null;

        Reserva r = new Reserva();
        r.setUsuario(usuario);
        r.setHuesped(huesped);
        r.setEmpresa(empresa);
        r.setFechaIngreso(LocalDateTime.parse(dto.getFechaIngreso()));
        r.setFechaSalida(LocalDateTime.parse(dto.getFechaSalida()));

        return reservaService.guardar(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody ReservaRequest dto) {
        Usuario usuario = usuarioService.obtenerPorId(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Huesped huesped = (dto.getHuespedId() != null)
                ? huespedService.obtenerPorId(dto.getHuespedId())
                .orElseThrow(() -> new RuntimeException("Huésped no encontrado"))
                : null;

        Empresa empresa = (dto.getEmpresaId() != null)
                ? empresaService.obtenerPorId(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"))
                : null;

        Reserva r = new Reserva();
        r.setUsuario(usuario);
        r.setHuesped(huesped);
        r.setEmpresa(empresa);
        r.setFechaIngreso(LocalDateTime.parse(dto.getFechaIngreso()));
        r.setFechaSalida(LocalDateTime.parse(dto.getFechaSalida()));

        Reserva actualizada = reservaService.actualizar(id, r);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}