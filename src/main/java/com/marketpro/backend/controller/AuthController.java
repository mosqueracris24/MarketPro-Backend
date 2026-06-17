package com.marketpro.backend.controller;

import com.marketpro.backend.model.Usuario;
import com.marketpro.backend.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UsuarioRepository repo;

    public AuthController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        Map<String, Object> response = new HashMap<>();

        Usuario usuario = repo.findByUsername(username).orElse(null);

        if (usuario == null) {
            response.put("exito", false);
            response.put("error", "Usuario no encontrado");
            return response;
        }

        if (!usuario.getPassword().equals(password)) {
            response.put("exito", false);
            response.put("error", "Contraseña incorrecta");
            return response;
        }

        if (!usuario.getIsActive()) {
            response.put("exito", false);
            response.put("error", "Usuario inactivo");
            return response;
        }

        response.put("exito", true);
        response.put("usuario", usuario);

        return response;
    }
}