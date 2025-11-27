package com.example.demo.controller;

import com.example.demo.entity.Examen;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.ExamenRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/alumno")
public class AlumnoPanelController {

    private final UsuarioRepository usuarioRepository;
    private final ExamenRepository examenRepository;

    public AlumnoPanelController(UsuarioRepository usuarioRepository,
                                 ExamenRepository examenRepository) {
        this.usuarioRepository = usuarioRepository;
        this.examenRepository = examenRepository;
    }

    // PANEL PRINCIPAL DEL ALUMNO
    @GetMapping("/panel")
    public String panelAlumno(HttpSession session, Model model) {

        Usuario alumno = (Usuario) session.getAttribute("usuarioLogueado");

        if (alumno == null) {
            return "redirect:/login";
        }

        // obtener el último examen del alumno (si hay varios, toma el más reciente)
        Examen examen = examenRepository.findTopByUsuario_NumdocOrderByIdDesc(alumno.getNumdoc());

        model.addAttribute("alumno", alumno);
        model.addAttribute("examen", examen);

        return "alumno/panel";
    }

    // INFORME ÚNICO
    @GetMapping("/informe-unico")
    public String informeUnico(HttpSession session, Model model) {

        Usuario alumno = (Usuario) session.getAttribute("usuarioLogueado");
        if (alumno == null) return "redirect:/login";

        Examen examen = examenRepository.findTopByUsuario_NumdocOrderByIdDesc(alumno.getNumdoc());

        model.addAttribute("alumno", alumno);
        model.addAttribute("examen", examen);

        return "alumno/informe-unico";
    }

    // INFORME DETALLADO
    @GetMapping("/informe-detallado")
    public String informeDetallado(HttpSession session, Model model) {

        Usuario alumno = (Usuario) session.getAttribute("usuarioLogueado");
        if (alumno == null) return "redirect:/login";

        Examen examen = examenRepository.findTopByUsuario_NumdocOrderByIdDesc(alumno.getNumdoc());

        model.addAttribute("alumno", alumno);
        model.addAttribute("examen", examen);

        return "alumno/informe-detallado";
    }

    // INFORME BENEFICIOS
    @GetMapping("/informe-beneficios")
    public String informeBeneficios(HttpSession session, Model model) {

        Usuario alumno = (Usuario) session.getAttribute("usuarioLogueado");
        if (alumno == null) return "redirect:/login";

        Examen examen = examenRepository.findTopByUsuario_NumdocOrderByIdDesc(alumno.getNumdoc());

        Integer total = (examen != null) ? examen.getTotal() : null;
        String beneficio = calcularBeneficio(total);

        model.addAttribute("alumno", alumno);
        model.addAttribute("examen", examen);
        model.addAttribute("beneficio", beneficio);

        return "alumno/informe-beneficios";
    }


    // LÓGICA DE BENEFICIOS (acepta null)
    private String calcularBeneficio(Integer total) {

        if (total == null) {
            return "Sin beneficios asignados.";
        }

        if (total >= 180 && total <= 210) {
            return "Se exonera del informe final o Seminario IV. Nota: 4.5.";
        }
        if (total >= 211 && total <= 240) {
            return "Exonerado del informe o Seminario IV (nota 4.7). 50% de beca en derechos de grado.";
        }
        if (total >= 241) {
            return "Exonerado del informe o Seminario IV (nota 5.0). 100% de beca en derechos de grado.";
        }

        return "Sin beneficios asignados.";
    }
}
