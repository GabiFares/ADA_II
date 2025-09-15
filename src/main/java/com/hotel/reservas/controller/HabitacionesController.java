package com.hotel.reservas.controller;

import com.hotel.reservas.dto.HabitacionRequest;
import com.hotel.reservas.model.Categoria;
import com.hotel.reservas.model.Estado;
import com.hotel.reservas.model.Habitacion;
import com.hotel.reservas.model.Tipo;
import com.hotel.reservas.service.CategoriaService;
import com.hotel.reservas.service.EstadoService;
import com.hotel.reservas.service.HabitacionService;
import com.hotel.reservas.service.TipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionesController {

    private final HabitacionService habitacionService;
    private final CategoriaService categoriaService;
    private final TipoService tipoService;
    private final EstadoService estadoService;

    public HabitacionesController(HabitacionService habitacionService, CategoriaService categoriaService,
                                TipoService tipoService, EstadoService estadoService) {
        this.habitacionService = habitacionService;
        this.categoriaService = categoriaService;
        this.tipoService = tipoService;
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtener(@PathVariable Long id) {
        return habitacionService.obtenerPorId(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habitacion crear(@RequestBody HabitacionRequest dto) {
        Categoria categoria = categoriaService.obtenerPorId(dto.getCategoriaId()).orElseThrow();
        Tipo tipo = tipoService.obtenerPorId(dto.getTipoId()).orElseThrow();
        Estado estado = estadoService.obtenerPorId(dto.getEstadoId()).orElseThrow();

        Habitacion h = new Habitacion();
        h.setAccesible(dto.isAccesible());
        h.setDisponible(dto.isDisponible());
        h.setPrecio(dto.getPrecio());
        h.setNumHabitacion(dto.getNumHabitacion());
        h.setObservaciones(dto.getObservaciones());
        h.setCategoria(categoria);
        h.setTipo(tipo);
        h.setEstado(estado);

        return habitacionService.guardar(h);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizar(@PathVariable Long id, @RequestBody HabitacionRequest dto) {
        Categoria categoria = categoriaService.obtenerPorId(dto.getCategoriaId()).orElseThrow();
        Tipo tipo = tipoService.obtenerPorId(dto.getTipoId()).orElseThrow();
        Estado estado = estadoService.obtenerPorId(dto.getEstadoId()).orElseThrow();

        Habitacion h = new Habitacion();
        h.setAccesible(dto.isAccesible());
        h.setDisponible(dto.isDisponible());
        h.setPrecio(dto.getPrecio());
        h.setNumHabitacion(dto.getNumHabitacion());
        h.setObservaciones(dto.getObservaciones());
        h.setCategoria(categoria);
        h.setTipo(tipo);
        h.setEstado(estado);

        Habitacion actualizada = habitacionService.actualizar(id, h);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}