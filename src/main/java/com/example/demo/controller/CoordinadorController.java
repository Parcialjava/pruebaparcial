package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoordinadorController {

    @GetMapping("/coordinador/panel")
    public String coordinadorPanel() {
        return "coordinador/panel";
    }
}
