package com.equals.projetoequals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint simples de saúde.
 * Retorna texto puro confirmando que a aplicação está online.
 */
@RestController
public class MainController {

    @GetMapping("/")
    public String hello() {
        return "Aplicação Projeto-Equals rodando!";
    }
}
