package com.hotel.reservas.service;

import com.hotel.reservas.model.Tipo;
import com.hotel.reservas.repository.TipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    private final TipoRepository repository;

    public TipoService(TipoRepository repository) {
        this.repository = repository;
    }

    public List<Tipo> listar() {
        return repository.findAll();
    }

    public Optional<Tipo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Tipo guardar(Tipo t) {
        return repository.save(t);
    }

    public Tipo actualizar(Long id, Tipo nuevo) {
        return repository.findById(id)
                .map(t -> {
                    t.setNombre(nuevo.getNombre());
                    t.setDescripcion(nuevo.getDescripcion());
                    return repository.save(t);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}