package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/alumnos")
public class AlumnoController {

    private final UsuarioRepository usuarioRepository;

    public AlumnoController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // LISTAR ALUMNOS
    @GetMapping
    public String listarAlumnos(Model model) {
        model.addAttribute("alumnos", usuarioRepository.findByRol("ALUMNO"));
        return "admin/alumnos/list"; // vista
    }

    // FORMULARIO DE NUEVO ALUMNO
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        Usuario alumno = new Usuario();
        alumno.setRol("ALUMNO");
        model.addAttribute("alumno", alumno);
        return "admin/alumnos/form";
    }

    // GUARDAR NUEVO ALUMNO
    @PostMapping("/guardar")
    public String guardarAlumno(@ModelAttribute("alumno") Usuario alumno) {
        alumno.setRol("ALUMNO"); 
        usuarioRepository.save(alumno);
        return "redirect:/admin/alumnos";
    }

    // EDITAR ALUMNO
    @GetMapping("/editar/{id}")
    public String editarAlumno(@PathVariable Long id, Model model) {
        Usuario alumno = usuarioRepository.findById(id).orElse(null);

        if (alumno == null || !"ALUMNO".equals(alumno.getRol())) {
            return "redirect:/admin/alumnos";
        }

        model.addAttribute("alumno", alumno);
        return "admin/alumnos/form";
    }

    // ELIMINAR ALUMNO
    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        Usuario alumno = usuarioRepository.findById(id).orElse(null);

        if (alumno != null && "ALUMNO".equals(alumno.getRol())) {
            usuarioRepository.delete(alumno);
        }

        return "redirect:/admin/alumnos";
    }
}
