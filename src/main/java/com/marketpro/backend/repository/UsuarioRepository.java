package com.marketpro.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marketpro.backend.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // 🔍 Buscar por username
    Optional<Usuario> findByUsername(String username);

    // 🔍 Buscar por email
    Optional<Usuario> findByEmail(String email);
}