package com.marketpro.backend.jdbc;

import com.marketpro.backend.model.Usuario;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioJdbcService {

    private final String URL = "jdbc:mysql://localhost:3306/marketpro";
    private final String USER = "root";
    private final String PASSWORD = "";

    public List<Usuario> listarUsuarios() {

        List<Usuario> usuarios = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            String sql = "SELECT * FROM usuario";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setUsername(
                        rs.getString("username")
                );

                usuario.setEmail(
                        rs.getString("email")
                );

                usuario.setRole(
                        rs.getString("role")
                );

                usuario.setIsActive(
                        rs.getBoolean("is_active")
                );

                usuarios.add(usuario);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}