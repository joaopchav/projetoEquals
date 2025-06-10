package com.equals.projetoequals.service;

import com.equals.projetoequals.model.Venda;
import com.equals.projetoequals.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class LeitorArquivoService {

    private final VendaRepository vendaRepository;

    public LeitorArquivoService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void lerArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            while ((linha = br.readLine()) != null) {
                // Processa apenas linhas de detalhe (tipo 1)
                if (linha.startsWith("1")) {
                    Venda venda = new Venda();

                    // Exemplo de extração (ajuste os índices conforme o layout oficial)
                    String dataStr = linha.substring(20, 28);
                    String codigoTransacao = linha.substring(46, 78).trim();
                    String valorStr = linha.substring(98, 111);

                    // Conversões
                    LocalDate data = LocalDate.parse(dataStr, formatter);
                    Double valor = Double.parseDouble(valorStr) / 100.0;

                    venda.setDataEvento(data);
                    venda.setCodigoTransacao(codigoTransacao);
                    venda.setValorTotal(valor);

                    // Salvar no banco
                    vendaRepository.save(venda);
                }
            }

            System.out.println("✅ Arquivo processado com sucesso.");

        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
