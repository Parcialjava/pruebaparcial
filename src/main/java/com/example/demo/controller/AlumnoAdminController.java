package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coordinador/alumnos")
public class AlumnoAdminController {

    private final UsuarioRepository usuarioRepository;

    public AlumnoAdminController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // LISTAR ALUMNOS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alumnos", usuarioRepository.findByRol("ALUMNO"));
        return "coordinador/alumnos/lista";
    }

    // CREAR - FORMULARIO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alumno", new Usuario());
        return "coordinador/alumnos/form";
    }

    // GUARDAR ALUMNO
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario alumno) {
        alumno.setRol("ALUMNO");
        usuarioRepository.save(alumno);
        return "redirect:/coordinador/alumnos";
    }

    // EDITAR ALUMNO
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario alumno = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("alumno", alumno);
        return "coordinador/alumnos/form";
    }

    // ELIMINAR ALUMNO
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/coordinador/alumnos";
    }
}
