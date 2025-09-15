package com.hotel.reservas.service;
import com.hotel.reservas.dto.*;
import com.hotel.reservas.model.Usuario;
import com.hotel.reservas.model.Direccion;
import com.hotel.reservas.model.Persona;
import com.hotel.reservas.repository.UsuarioRepository;
import com.hotel.reservas.repository.PersonaRepository;
import com.hotel.reservas.repository.RolRepository;
import com.hotel.reservas.repository.DireccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;
    private final DireccionRepository direccionRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
   public UsuarioInfo login(LoginRequest login) {
    try {
        System.out.println("🔍 Buscando usuario: " + login.getNombreUsuario());
        
        Usuario user = usuarioRepository.findByNombreUsuario(login.getNombreUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        System.out.println("✅ Usuario encontrado: " + user.getNombreUsuario());
        System.out.println("🔐 Contraseña enviada: " + login.getContrasenia());
        System.out.println("🔐 Contraseña en BD: " + user.getContrasenia());

        if (!passwordEncoder.matches(login.getContrasenia(), user.getContrasenia())) {
            System.out.println("❌ Contraseña incorrecta");
            throw new RuntimeException("Contraseña incorrecta");
        }

        System.out.println("✅ Contraseña correcta");

        UsuarioInfo response = new UsuarioInfo();
        response.setId(user.getId());
        response.setNombre(user.getNombre());
        response.setApellido(user.getApellido());
        response.setNombreUsuario(user.getNombreUsuario());
        
        // Verificar que el rol existe
        if (user.getRol() != null) {
            response.setRol(user.getRol().getNombre());
            System.out.println("✅ Rol asignado: " + user.getRol().getNombre());
        } else {
            response.setRol("SIN_ROL");
            System.out.println("⚠️ Usuario sin rol asignado");
        }

        System.out.println("✅ Login exitoso para: " + user.getNombreUsuario());
        return response;
        
    } catch (Exception e) {
        System.out.println("❌ Error en login: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
    }

    @Transactional
    public void register(RegistroRequest request) {
        // Validar usuario duplicado
        if (usuarioRepository.findByNombreUsuario(request.getNombreUsuario()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        // Validar email duplicado
        if (personaRepository.findAll().stream().anyMatch(p -> p.getEmail().equals(request.getEmail()))) {
            throw new RuntimeException("El email ya está registrado");
        }
        // Buscar o crear dirección
        Direccion direccion = direccionRepository
            .findByCalleAndNumPuertaAndCiudadAndDepartamentoAndPais(
                request.getCalle(),
                request.getNumPuerta(),
                request.getCiudad(),
                request.getDepartamento(),
                request.getPais()
            )
            .orElseGet(() -> {
                Direccion nueva = new Direccion();
                nueva.setCalle(request.getCalle());
                nueva.setNumPuerta(request.getNumPuerta());
                nueva.setCiudad(request.getCiudad());
                nueva.setDepartamento(request.getDepartamento());
                nueva.setPais(request.getPais());
                return direccionRepository.save(nueva);
            });
        // Crear persona
        Persona persona = new Persona();
        persona.setTelefono(request.getTelefono());
        persona.setEmail(request.getEmail());
        persona.setDireccion(direccion);
        persona = personaRepository.save(persona);
        // Crear usuario
        Usuario nuevo = new Usuario();
        nuevo.setCi(request.getCi());
        nuevo.setNombre(request.getNombre());
        nuevo.setApellido(request.getApellido());
        nuevo.setNombreUsuario(request.getNombreUsuario());
        nuevo.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
        // Asignar rol según el id recibido
        nuevo.setRol(rolRepository.findById(request.getIdRol()).orElseThrow());
        nuevo.setPersona(persona);
        usuarioRepository.save(nuevo);
    }
}
