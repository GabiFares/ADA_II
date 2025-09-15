package com.hotel.reservas.service;

import com.hotel.reservas.model.ResSala;
import com.hotel.reservas.repository.ResSalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResSalaService {

    private final ResSalaRepository repository;

    public ResSalaService(ResSalaRepository repository) {
        this.repository = repository;
    }

    public List<ResSala> listar() {
        return repository.findAll();
    }

    public Optional<ResSala> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public ResSala guardar(ResSala r) {
        return repository.save(r);
    }

    public ResSala actualizar(Long id, ResSala nueva) {
        return repository.findById(id)
                .map(r -> {
                    r.setReserva(nueva.getReserva());
                    r.setSala(nueva.getSala());
                    return repository.save(r);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}