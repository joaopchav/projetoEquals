package com.equals.projetoequals.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔑 Campos extraídos do layout
    private String estabelecimento;
    private LocalDate dataInicial;
    private LocalDate dataEvento;
    private LocalTime horaEvento;
    private String tipoEvento;
    private String tipoTransacao;
    private String numSerieLeitor;
    private String codigoTransacao;
    private String codigoPedido;
    private Long valorTotal;
    private Long valorLiquido;
    private String tipoPagamento;
    private String plano;
    private String parcela;
    private String qtdParcelas;
    private LocalDate dataPrevPagto;
    private Long valorOriginal;
    private Long valorLiquidoFinal;
    private String statusPgto;
    private String meioPagamento;
    private String bandeira;
}
