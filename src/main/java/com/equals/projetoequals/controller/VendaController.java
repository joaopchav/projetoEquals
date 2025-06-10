package com.equals.projetoequals.controller;

import com.equals.projetoequals.model.Venda;
import com.equals.projetoequals.repository.VendaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaRepository repository;

    public VendaController(VendaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Venda> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/filtrar")
    public List<Venda> filtrarPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return repository.findByDataEventoBetween(inicio, fim);
    }
}
