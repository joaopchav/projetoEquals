package com.equals.projetoequals.controller;

import com.equals.projetoequals.model.Venda;
import com.equals.projetoequals.repository.VendaRepository;
import com.equals.projetoequals.service.LeitorArquivoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaRepository repo;
    private final LeitorArquivoService leitor;

    public VendaController(VendaRepository repo, LeitorArquivoService leitor) {
        this.repo = repo;
        this.leitor = leitor;
    }

    @GetMapping
    public List<Venda> listar() {
        return repo.findAll();
    }

    /** Filtros */

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/periodo")
    public List<Venda> porPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return repo.findByDataEventoBetween(inicio, fim);
    }

    @GetMapping("/bandeira")
    public List<Venda> buscarPorBandeira(@RequestParam String bandeira) {
        return repo.findByBandeira(bandeira);
    }

    @GetMapping("/parcelas")
    public List<Venda> porParcelas(@RequestParam String quantidade) {
        return repo.findByQtdParcelas(quantidade);
    }

    /** Faz upload e importa um novo arquivo. */
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {

        Path temp = Files.createTempFile("equals", ".txt");
        Files.write(temp, file.getBytes());

        leitor.importar(temp.toString());
        Files.delete(temp);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Arquivo processado com sucesso");
    }
}
