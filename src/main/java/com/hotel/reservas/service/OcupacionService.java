package com.hotel.reservas.service;

import com.hotel.reservas.model.Ocupacion;
import com.hotel.reservas.repository.OcupacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcupacionService {

    private final OcupacionRepository repository;

    public OcupacionService(OcupacionRepository repository) {
        this.repository = repository;
    }

    public List<Ocupacion> listar() {
        return repository.findAll();
    }

    public Optional<Ocupacion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Ocupacion guardar(Ocupacion o) {
        return repository.save(o);
    }

    public Ocupacion actualizar(Long id, Ocupacion nueva) {
        return repository.findById(id)
                .map(o -> {
                    o.setReserva(nueva.getReserva());
                    o.setHabitacion(nueva.getHabitacion());
                    o.setHuesped(nueva.getHuesped());
                    o.setFechaIngreso(nueva.getFechaIngreso());
                    o.setFechaFin(nueva.getFechaFin());
                    return repository.save(o);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}