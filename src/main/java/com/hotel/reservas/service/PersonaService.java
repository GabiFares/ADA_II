package com.hotel.reservas.service;

import com.hotel.reservas.model.Persona;
import com.hotel.reservas.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository repository;

    public PersonaService(PersonaRepository repository) {
        this.repository = repository;
    }

    public List<Persona> listar() {
        return repository.findAll();
    }

    public Optional<Persona> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Persona guardar(Persona p) {
        return repository.save(p);
    }

    public Persona actualizar(Long id, Persona nueva) {
        return repository.findById(id)
                .map(p -> {
                    p.setTelefono(nueva.getTelefono());
                    p.setEmail(nueva.getEmail());
                    p.setDireccion(nueva.getDireccion());
                    return repository.save(p);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}