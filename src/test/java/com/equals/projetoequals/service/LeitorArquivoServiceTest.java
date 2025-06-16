package com.equals.projetoequals.service;

import com.equals.projetoequals.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LeitorArquivoServiceTest {

    @Autowired
    private LeitorArquivoService service;

    @Autowired
    private VendaRepository repo;

    @Test
    void deveImportarArquivoExemplo() throws Exception {

        // Arquivo localizado na raiz do projeto (projetoEquals/Arquivo_...txt)
        String caminho = Paths.get("Arquivo_Estagio_Desenvolvimento.txt")
                .toAbsolutePath()
                .toString();

        service.importar(caminho);

        // Deve ter importado pelo menos uma venda
        assertThat(repo.count()).isGreaterThan(0);
    }
}
