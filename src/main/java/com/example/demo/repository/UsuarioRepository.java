package com.example.demo.repository;

import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByRol(String rol);
    Usuario findByNumdocAndPassword(String numdoc, String password); // login por numdoc + password
    Usuario findByNumdoc(String numdoc); // buscar por identificación
}
