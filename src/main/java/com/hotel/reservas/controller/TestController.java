package com.hotel.reservas.controller;

import com.hotel.reservas.model.Usuario;
import com.hotel.reservas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "¡Bienvenido a la API del Hotel Reservas!");
        response.put("version", "1.0.0");
        response.put("description", "Sistema de gestión de reservas de hotel");
        response.put("endpoints", Map.of(
            "test", "/api/test/ping",
            "info", "/api/test/info",
            "auth", "/api/auth/**",
            "reservas", "/api/reservas/**",
            "habitaciones", "/api/habitaciones/**",
            "usuarios", "/api/usuarios/**"
        ));
        response.put("timestamp", LocalDateTime.now());
        return response;
    }

    @GetMapping("/api/test/ping")
    public Map<String, Object> ping() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "API funcionando correctamente");
        response.put("timestamp", LocalDateTime.now());
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "Hotel Reservas API");
        response.put("version", "1.0.0");
        response.put("description", "Sistema de gestión de reservas de hotel");
        response.put("timestamp", LocalDateTime.now());
        return response;
    }

    @PostMapping("/api/test/login-debug")
    public Map<String, Object> loginDebug(@RequestBody Map<String, String> loginData) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("received_data", loginData);
            response.put("status", "success");
            response.put("message", "Datos recibidos correctamente");
            response.put("timestamp", LocalDateTime.now());
        } catch (Exception e) {
            response.put("error", e.getMessage());
            response.put("status", "error");
        }
        return response;
    }

    @PostMapping("/api/test/simple-login")
    public Map<String, Object> simpleLogin(@RequestBody Map<String, String> loginData) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("🔍 TestController - Datos recibidos: " + loginData);
            
            String nombreUsuario = loginData.get("nombreUsuario");
            String contrasenia = loginData.get("contrasenia");
            
            System.out.println("🔍 Usuario: " + nombreUsuario);
            System.out.println("🔍 Contraseña: " + contrasenia);
            
            // Simular respuesta exitosa
            response.put("id", 1L);
            response.put("nombre", "Test");
            response.put("apellido", "User");
            response.put("nombreUsuario", nombreUsuario);
            response.put("rol", "ADMIN");
            response.put("status", "success");
            response.put("message", "Login simulado exitoso");
            response.put("timestamp", LocalDateTime.now());
            
            System.out.println("✅ TestController - Respuesta generada exitosamente");
            
        } catch (Exception e) {
            System.out.println("❌ TestController - Error: " + e.getMessage());
            e.printStackTrace();
            response.put("error", e.getMessage());
            response.put("status", "error");
        }
        return response;
    }

    @PostMapping("/api/test/working-login")
    public Map<String, Object> workingLogin(@RequestBody Map<String, String> loginData) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("🔍 WorkingLogin - Iniciando login");
            
            String nombreUsuario = loginData.get("nombreUsuario");
            String contrasenia = loginData.get("contrasenia");
            
            System.out.println("🔍 WorkingLogin - Usuario: " + nombreUsuario);
            System.out.println("🔍 WorkingLogin - PasswordEncoder: " + (passwordEncoder != null ? "OK" : "NULL"));
            
            // Buscar usuario en la base de datos
            Usuario user = usuarioRepository.findByNombreUsuario(nombreUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            System.out.println("✅ WorkingLogin - Usuario encontrado: " + user.getNombreUsuario());
            System.out.println("🔐 WorkingLogin - Contraseña enviada: " + contrasenia);
            System.out.println("🔐 WorkingLogin - Contraseña en BD: " + user.getContrasenia());
            
            // Crear un nuevo encoder para probar
            BCryptPasswordEncoder testEncoder = new BCryptPasswordEncoder();
            System.out.println("🔐 WorkingLogin - Test encoder creado");
            
            // Verificar contraseña
            boolean matches = testEncoder.matches(contrasenia, user.getContrasenia());
            System.out.println("🔐 WorkingLogin - Resultado de verificación: " + matches);
            
            if (!matches) {
                System.out.println("❌ WorkingLogin - Contraseña incorrecta");
                throw new RuntimeException("Contraseña incorrecta");
            }
            
            System.out.println("✅ WorkingLogin - Contraseña correcta");
            
            // Crear respuesta
            response.put("id", user.getId());
            response.put("nombre", user.getNombre());
            response.put("apellido", user.getApellido());
            response.put("nombreUsuario", user.getNombreUsuario());
            response.put("rol", user.getRol() != null ? user.getRol().getNombre() : "SIN_ROL");
            response.put("status", "success");
            response.put("message", "Login exitoso");
            response.put("timestamp", LocalDateTime.now());
            
            System.out.println("✅ WorkingLogin - Login exitoso para: " + user.getNombreUsuario());
            
        } catch (Exception e) {
            System.out.println("❌ WorkingLogin - Error: " + e.getMessage());
            e.printStackTrace();
            response.put("error", e.getMessage());
            response.put("status", "error");
        }
        return response;
    }
}
