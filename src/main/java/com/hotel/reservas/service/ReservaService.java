package com.hotel.reservas.service;

import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Reserva guardar(Reserva r) {
        return repository.save(r);
    }

    public Reserva actualizar(Long id, Reserva nueva) {
        return repository.findById(id)
                .map(r -> {
                    r.setFechaIngreso(nueva.getFechaIngreso());
                    r.setFechaSalida(nueva.getFechaSalida());
                    r.setUsuario(nueva.getUsuario());
                    r.setHuesped(nueva.getHuesped());
                    r.setEmpresa(nueva.getEmpresa());
                    return repository.save(r);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}