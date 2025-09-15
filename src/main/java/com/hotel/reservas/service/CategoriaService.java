package com.hotel.reservas.service;

import com.hotel.reservas.model.Categoria;
import com.hotel.reservas.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Optional<Categoria> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Categoria guardar(Categoria c) {
        return repository.save(c);
    }

    public Categoria actualizar(Long id, Categoria nueva) {
        return repository.findById(id)
                .map(c -> {
                    c.setNombre(nueva.getNombre());
                    c.setDescripcion(nueva.getDescripcion());
                    return repository.save(c);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}