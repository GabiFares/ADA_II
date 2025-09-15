package com.hotel.reservas.service;

import com.hotel.reservas.model.Empresa;
import com.hotel.reservas.model.Huesped;
import com.hotel.reservas.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    public List<Empresa> listar() {
        return repository.findAll();
    }

    public Optional<Empresa> obtenerPorId(Long id) {
        return repository.findById(id);
    }
    public Optional<Empresa> obtenerPorRut(String rut) {
    return repository.findByRut(rut);
}

    public Empresa guardar(Empresa e) {
        return repository.save(e);
    }

    public Empresa actualizar(Long id, Empresa nueva) {
        return repository.findById(id)
                .map(e -> {
                    e.setRut(nueva.getRut());
                    e.setNomFantasia(nueva.getNomFantasia());
                    e.setRazonSocial(nueva.getRazonSocial());
                    e.setPersona(nueva.getPersona());
                    return repository.save(e);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}