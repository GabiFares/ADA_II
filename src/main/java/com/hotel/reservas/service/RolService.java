package com.hotel.reservas.service;

import com.hotel.reservas.model.Rol;
import com.hotel.reservas.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository repository;

    public RolService(RolRepository repository) {
        this.repository = repository;
    }

    public List<Rol> listar() {
        return repository.findAll();
    }

    public Optional<Rol> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Rol guardar(Rol r) {
        return repository.save(r);
    }

    public Rol actualizar(Long id, Rol nueva) {
        return repository.findById(id)
                .map(r -> {
                    r.setNombre(nueva.getNombre());
                    r.setDescripcion(nueva.getDescripcion());
                    return repository.save(r);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}