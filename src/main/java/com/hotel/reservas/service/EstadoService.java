package com.hotel.reservas.service;

import com.hotel.reservas.model.Estado;
import com.hotel.reservas.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> listar() {
        return repository.findAll();
    }

    public Optional<Estado> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Estado guardar(Estado e) {
        return repository.save(e);
    }

    public Estado actualizar(Long id, Estado nuevo) {
        return repository.findById(id)
                .map(e -> {
                    e.setNombre(nuevo.getNombre());
                    e.setDescripcion(nuevo.getDescripcion());
                    return repository.save(e);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}