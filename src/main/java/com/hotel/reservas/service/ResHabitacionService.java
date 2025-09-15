package com.hotel.reservas.service;

import com.hotel.reservas.model.ResHabitacion;
import com.hotel.reservas.repository.ResHabitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResHabitacionService {

    private final ResHabitacionRepository repository;

    public ResHabitacionService(ResHabitacionRepository repository) {
        this.repository = repository;
    }

    public List<ResHabitacion> listar() {
        return repository.findAll();
    }

    public Optional<ResHabitacion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public ResHabitacion guardar(ResHabitacion r) {
        return repository.save(r);
    }

    public ResHabitacion actualizar(Long id, ResHabitacion nueva) {
        return repository.findById(id)
                .map(r -> {
                    r.setReserva(nueva.getReserva());
                    r.setHabitacion(nueva.getHabitacion());
                    return repository.save(r);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}