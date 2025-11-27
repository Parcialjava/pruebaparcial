package com.example.demo.repository;

import com.example.demo.entity.Examen;
import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findByUsuario_Numdoc(String numdoc);
    Examen findTopByUsuario_NumdocOrderByIdDesc(String numdoc);

    @Query("SELECT u FROM Usuario u WHERE u.rol = 'ALUMNO' AND u.id NOT IN (SELECT e.usuario.id FROM Examen e)")
    List<Usuario> findAlumnosSinExamen();
}
