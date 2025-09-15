package com.hotel.reservas.controller;

import com.hotel.reservas.dto.*;
import com.hotel.reservas.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public UsuarioInfo login(@RequestBody LoginRequest login, HttpServletRequest request) {
        UsuarioInfo info = authService.login(login);
        HttpSession session = request.getSession(true); // inicia sesión
        session.setAttribute("usuario", info);

        var auth = new UsernamePasswordAuthenticationToken(
            info,
            null,
            List.of(new SimpleGrantedAuthority("ROLE_" + info.getRol()))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(auth);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);   
        return info;
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession(false).invalidate(); // destruye sesión
    }

    @GetMapping("/check")
    public UsuarioInfo check(HttpServletRequest request) {
        return (UsuarioInfo) request.getSession(false).getAttribute("usuario");
    }

    @PostMapping("/registro")
    public void registro(@RequestBody RegistroRequest request) {
        authService.register(request);
    }
}


