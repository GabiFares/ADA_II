package com.hotel.reservas.controller;

import com.hotel.reservas.dto.ResSalaRequest;
import com.hotel.reservas.model.ResSala;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.model.Sala;
import com.hotel.reservas.service.ResSalaService;
import com.hotel.reservas.service.ReservaService;
import com.hotel.reservas.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/res_salas")
public class Res_salasController {

    private final ResSalaService resSalaService;
    private final ReservaService reservaService;
    private final SalaService salaService;

    public Res_salasController(ResSalaService resSalaService, ReservaService reservaService, SalaService salaService) {
        this.resSalaService = resSalaService;
        this.reservaService = reservaService;
        this.salaService = salaService;
    }

    @GetMapping
    public List<ResSala> listar() {
        return resSalaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResSala> obtener(@PathVariable Long id) {
        return resSalaService.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResSala crear(@RequestBody ResSalaRequest dto) {
        Reserva reserva = reservaService.obtenerPorId(dto.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Sala sala = salaService.obtenerPorId(dto.getSalaId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        ResSala rs = new ResSala();
        rs.setReserva(reserva);
        rs.setSala(sala);

        return resSalaService.guardar(rs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResSala> actualizar(@PathVariable Long id, @RequestBody ResSalaRequest dto) {
        Reserva reserva = reservaService.obtenerPorId(dto.getReservaId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        Sala sala = salaService.obtenerPorId(dto.getSalaId())
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        ResSala rs = new ResSala();
        rs.setReserva(reserva);
        rs.setSala(sala);

        ResSala actualizada = resSalaService.actualizar(id, rs);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resSalaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}