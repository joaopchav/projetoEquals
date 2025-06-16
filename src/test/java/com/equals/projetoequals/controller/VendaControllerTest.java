package com.equals.projetoequals.controller;

import com.equals.projetoequals.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VendaController.class)
class VendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendaRepository repo;
    @MockBean
    private com.equals.projetoequals.service.LeitorArquivoService service;

    @Test
    void deveRetornarListaVazia() throws Exception {
        when(repo.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/vendas"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
