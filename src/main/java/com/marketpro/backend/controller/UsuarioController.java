package com.marketpro.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.marketpro.backend.model.Usuario;
import com.marketpro.backend.repository.UsuarioRepository;
import com.marketpro.backend.jdbc.UsuarioJdbcService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioJdbcService usuarioJdbcService;

    // ============================
    // LISTAR CON JPA
    // ============================
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    // ============================
    // LISTAR CON JDBC
    // ============================
    @GetMapping("/jdbc")
    public List<Usuario> listarJdbc() {
        return usuarioJdbcService.listarUsuarios();
    }

    // ============================
    // CREAR
    // ============================
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ============================
    // ACTUALIZAR
    // ============================
    @PutMapping("/{id}")
    public Usuario actualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existente.setUsername(usuario.getUsername());
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());
        existente.setRole(usuario.getRole());
        existente.setIsActive(usuario.getIsActive());

        return usuarioRepository.save(existente);
    }

    // ============================
    // ELIMINAR
    // ============================
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}