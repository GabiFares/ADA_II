package com.hotel.reservas.service;

import com.hotel.reservas.model.Huesped;
import com.hotel.reservas.repository.HuespedRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuespedService {

    private final HuespedRepository repository;

    public HuespedService(HuespedRepository repository) {
        this.repository = repository;
    }

    public List<Huesped> listar() {
        return repository.findAll();
    }

    public Optional<Huesped> obtenerPorId(Long id) {
        return repository.findById(id);
        
    }
    public Optional<Huesped> obtenerPorCi(String ci) {
        return repository.findByCi(ci);
    }
    

    public Huesped guardar(Huesped h) {
        return repository.save(h);
    }

    public Huesped actualizar(Long id, Huesped nuevo) {
        return repository.findById(id)
                .map(h -> {
                    h.setCi(nuevo.getCi());
                    h.setNombre(nuevo.getNombre());
                    h.setApellido(nuevo.getApellido());
                    h.setPersona(nuevo.getPersona());
                    return repository.save(h);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}