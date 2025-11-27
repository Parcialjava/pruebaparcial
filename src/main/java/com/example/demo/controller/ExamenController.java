package com.example.demo.controller;

import com.example.demo.entity.Examen;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ExamenRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/coordinador/examenes")
public class ExamenController {

    private final ExamenRepository examenRepository;
    private final UsuarioRepository usuarioRepository;

    public ExamenController(ExamenRepository examenRepository, UsuarioRepository usuarioRepository) {
        this.examenRepository = examenRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // LISTAR
    @GetMapping
    public String listarExamenes(Model model) {
        List<Examen> examenes = examenRepository.findAll();
        model.addAttribute("examenes", examenes);
        return "examenes/lista";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevoExamen(Model model) {
        Examen examen = new Examen();
        model.addAttribute("examen", examen);

        List<Usuario> alumnos = usuarioRepository.findByRol("ALUMNO");
        List<Usuario> alumnosSinExamen = alumnos.stream()
                .filter(a -> examenRepository.findByUsuario_Numdoc(a.getNumdoc()).isEmpty())
                .collect(Collectors.toList());

        model.addAttribute("alumnosSinExamen", alumnosSinExamen);
        return "examenes/form";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardarExamen(@ModelAttribute Examen examen) {
        if (examen.getUsuario() != null && examen.getUsuario().getId() != null) {
            Usuario alumno = usuarioRepository.findById(examen.getUsuario().getId()).orElse(null);
            examen.setUsuario(alumno);
            examenRepository.save(examen);
        }
        return "redirect:/coordinador/examenes";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Examen examen = examenRepository.findById(id).orElse(new Examen());
        model.addAttribute("examen", examen);

        List<Usuario> alumnos = usuarioRepository.findByRol("ALUMNO");

        List<Usuario> alumnosSinExamen = alumnos.stream()
                .filter(a -> examenRepository.findByUsuario_Numdoc(a.getNumdoc()).isEmpty()
                        || a.getId().equals(examen.getUsuario().getId()))
                .collect(Collectors.toList());

        model.addAttribute("alumnosSinExamen", alumnosSinExamen);
        return "examenes/form";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        examenRepository.deleteById(id);
        return "redirect:/coordinador/examenes";
    }


    // ======================================================================================
    //                                   INFORMES
    // ======================================================================================

    // 🔵 INFORME ÚNICO (Datos alumno + puntaje total)
    @GetMapping("/informe-unico/{id}")
    public String informeUnico(@PathVariable Long id, Model model) {
        Examen examen = examenRepository.findById(id).orElse(null);

        model.addAttribute("examen", examen);
        model.addAttribute("alumno", examen.getUsuario());

        return "examenes/informe_unico";
    }

    // 🔵 INFORME DETALLADO (Datos alumno + total + puntajes por materia)
    @GetMapping("/informe-detallado/{id}")
    public String informeDetallado(@PathVariable Long id, Model model) {
        Examen examen = examenRepository.findById(id).orElse(null);

        model.addAttribute("examen", examen);
        model.addAttribute("alumno", examen.getUsuario());

        return "examenes/informe_detallado";
    }

    // 🔵 INFORME BENEFICIOS
    @GetMapping("/informe-beneficios/{id}")
    public String informeBeneficios(@PathVariable Long id, Model model) {

        Examen examen = examenRepository.findById(id).orElse(null);

        int total = examen.getTotal();
        String beneficio = "";

        if (total >= 180 && total <= 210) {
            beneficio = "Exoneración del informe final o Seminario IV con nota 4.5";
        } else if (total >= 211 && total <= 240) {
            beneficio = "Exoneración del informe final o Seminario IV con nota 4.7 + 50% de descuento en derechos de grado";
        } else if (total >= 241) {
            beneficio = "Exoneración del informe final o Seminario IV con nota 5.0 + 100% de descuento en derechos de grado";
        } else {
            beneficio = "El estudiante no aplica para beneficios";
        }

        model.addAttribute("examen", examen);
        model.addAttribute("alumno", examen.getUsuario());
        model.addAttribute("beneficio", beneficio);

        return "examenes/informe_beneficios";
    }
}

