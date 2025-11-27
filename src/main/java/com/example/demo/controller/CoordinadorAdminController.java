package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/coordinadores")
public class CoordinadorAdminController {

    private final UsuarioRepository usuarioRepository;

    public CoordinadorAdminController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // LISTAR COORDINADORES
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("coordinadores", usuarioRepository.findByRol("COORDINADOR"));
        return "admin/coordinadores/lista";
    }

    // CREAR - FORMULARIO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("coordinador", new Usuario());
        return "admin/coordinadores/form";
    }

    // GUARDAR COORDINADOR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario coordinador) {
        coordinador.setRol("COORDINADOR");
        usuarioRepository.save(coordinador);
        return "redirect:/admin/coordinadores";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario coordinador = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("coordinador", coordinador);
        return "admin/coordinadores/form";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/coordinadores";
    }
}
