package com.hotel.reservas.service;

import com.hotel.reservas.model.Usuario;
import com.hotel.reservas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario guardar(Usuario u) {
        return repository.save(u);
    }

    public Usuario actualizar(Long id, Usuario nuevo) {
        return repository.findById(id)
                .map(u -> {
                    u.setNombre(nuevo.getNombre());
                    u.setApellido(nuevo.getApellido());
                    u.setCi(nuevo.getCi());
                    u.setPersona(nuevo.getPersona());
                    u.setRol(nuevo.getRol());
                    return repository.save(u);
                }).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}