package com.hotel.reservas.controller;

import com.hotel.reservas.dto.UsuarioRequest;
import com.hotel.reservas.model.Persona;
import com.hotel.reservas.model.Rol;
import com.hotel.reservas.model.Usuario;
import com.hotel.reservas.service.PersonaService;
import com.hotel.reservas.service.RolService;
import com.hotel.reservas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    private final UsuarioService usuarioService;
    private final PersonaService personaService;
    private final RolService rolService;

    public UsuariosController(UsuarioService usuarioService, PersonaService personaService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.personaService = personaService;
        this.rolService = rolService;
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crear(@RequestBody UsuarioRequest dto) {
        Persona persona = personaService.obtenerPorId(dto.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Rol rol = rolService.obtenerPorId(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setCi(dto.getCi());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setPersona(persona);
        usuario.setRol(rol);

        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody UsuarioRequest dto) {
        Persona persona = personaService.obtenerPorId(dto.getPersonaId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Rol rol = rolService.obtenerPorId(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setCi(dto.getCi());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setPersona(persona);
        usuario.setRol(rol);

        Usuario actualizado = usuarioService.actualizar(id, usuario);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}