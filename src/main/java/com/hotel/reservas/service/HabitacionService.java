package com.hotel.reservas.service;

import com.hotel.reservas.model.Habitacion;
import com.hotel.reservas.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    private final HabitacionRepository repository;

    public HabitacionService(HabitacionRepository repository) {
        this.repository = repository;
    }

    public List<Habitacion> listar() {
        return repository.findAll();
    }

    public Optional<Habitacion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Habitacion guardar(Habitacion h) {
        return repository.save(h);
    }

    public Habitacion actualizar(Long id, Habitacion nueva) {
        return repository.findById(id)
                .map(h -> {
                    h.setAccesible(nueva.isAccesible());
                    h.setDisponible(nueva.isDisponible());
                    h.setPrecio(nueva.getPrecio());
                    h.setNumHabitacion(nueva.getNumHabitacion());
                    h.setObservaciones(nueva.getObservaciones());
                    h.setCategoria(nueva.getCategoria());
                    h.setTipo(nueva.getTipo());
                    h.setEstado(nueva.getEstado());
                    return repository.save(h);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}