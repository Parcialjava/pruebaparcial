package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Mostrar login desde "/"
    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    // Mostrar login directamente desde "/login" (IMPORTANTE)
    @GetMapping("/login")
    public String mostrarLoginDirecto() {
        return "login";
    }

    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("numdoc") String numdoc,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        Usuario u = usuarioRepository.findByNumdoc(numdoc);

        if (u == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }

        if (!u.getPassword().equals(password)) {
            model.addAttribute("error", "Contraseña incorrecta");
            return "login";
        }

        // CORREGIDO: estabas usando usuarioLogeado (con una sola g)
        session.setAttribute("usuarioLogueado", u);

        // redirección según rol
        switch (u.getRol().toUpperCase()) {
            case "ADMIN":
                return "redirect:/admin/panel";
            case "COORDINADOR":
                return "redirect:/coordinador/panel";
            case "ALUMNO":
                return "redirect:/alumno/panel";
            default:
                model.addAttribute("error", "Rol desconocido");
                return "login";
        }
    }

    // Cerrar sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
