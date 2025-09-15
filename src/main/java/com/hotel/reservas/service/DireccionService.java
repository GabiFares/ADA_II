package com.hotel.reservas.service;

import com.hotel.reservas.model.Direccion;
import com.hotel.reservas.repository.DireccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    private final DireccionRepository repository;

    public DireccionService(DireccionRepository repository) {
        this.repository = repository;
    }

    public List<Direccion> listar() {
        return repository.findAll();
    }

    public Optional<Direccion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Direccion guardar(Direccion d) {
        return repository.save(d);
    }

    public Direccion actualizar(Long id, Direccion nueva) {
        return repository.findById(id)
                .map(d -> {
                    d.setCalle(nueva.getCalle());
                    d.setCiudad(nueva.getCiudad());
                    d.setDepartamento(nueva.getDepartamento());
                    d.setNumPuerta(nueva.getNumPuerta());
                    d.setPais(nueva.getPais());
                    return repository.save(d);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}