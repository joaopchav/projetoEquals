package com.equals.projetoequals.service;

import com.equals.projetoequals.model.Venda;
import com.equals.projetoequals.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class LeitorArquivoService {

    private final VendaRepository repo;
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HHmmss");

    public LeitorArquivoService(VendaRepository repo) {
        this.repo = repo;
    }

    /** Lê o arquivo linha a linha e salva no banco. */
    @Transactional
    public void importar(String caminho) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                // header(0) e trailer(9) são ignorados
                if (!linha.startsWith("1")) continue;

                Venda v = new Venda();

                v.setEstabelecimento(linha.substring(1, 11).trim());
                v.setDataInicial(LocalDate.parse(linha.substring(11, 19), DATE_FMT));
                v.setDataEvento(LocalDate.parse(linha.substring(19, 27), DATE_FMT));
                v.setHoraEvento(LocalTime.parse(linha.substring(27, 33), TIME_FMT));

                v.setTipoEvento(linha.substring(33, 35));
                v.setTipoTransacao(linha.substring(35, 37));
                v.setNumSerieLeitor(linha.substring(37, 45).trim());

                v.setCodigoTransacao(linha.substring(45, 77).trim());
                v.setCodigoPedido(linha.substring(77, 97).trim());

                v.setValorTotal(parseMoney(linha.substring(97, 110)));
                v.setValorLiquido(parseMoney(linha.substring(110, 123)));

                v.setTipoPagamento(linha.substring(123, 124));
                v.setPlano(linha.substring(124, 126));
                v.setParcela(linha.substring(126, 128));
                v.setQtdParcelas(linha.substring(128, 130));

                v.setDataPrevPagto(LocalDate.parse(linha.substring(130, 138), DATE_FMT));
                v.setValorOriginal(parseMoney(linha.substring(164, 177)));
                v.setValorLiquidoFinal(parseMoney(linha.substring(242, 255)));

                v.setStatusPgto(linha.substring(255, 257));
                v.setMeioPagamento(linha.substring(259, 261));
                v.setBandeira(linha.substring(261, 291).trim());

                repo.save(v);
            }
        }
    }

    private Long parseMoney(String str) {

        return Long.parseLong(str.replaceFirst("^0+(?!$)", ""));
    }
}
