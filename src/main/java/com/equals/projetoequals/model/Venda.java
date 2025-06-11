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
    private String estabelecimento;            // col  2-11 (10)
    private LocalDate dataInicial;             // col 12-19 (8)    AAAAMMDD
    private LocalDate dataEvento;              // col 20-27 (8)    AAAAMMDD
    private LocalTime horaEvento;              // col 28-33 (6)    HHMMSS
    private String tipoEvento;                 // col 34-35 (2)
    private String tipoTransacao;              // col 36-37 (2)
    private String numSerieLeitor;             // col 38-45 (8)
    private String codigoTransacao;            // col 46-77 (32)
    private String codigoPedido;               // col 78-97 (20)
    private Long valorTotal;                   // col 98-110 (13)  (valor em centavos)
    private Long valorLiquido;                 // col 111-123(13)
    private String tipoPagamento;              // col 124-124(1)
    private String plano;                      // col 125-126(2)
    private String parcela;                    // col 127-128(2)
    private String qtdParcelas;                // col 129-130(2)
    private LocalDate dataPrevPagto;           // col 131-138(8)
    private Long valorOriginal;                // col 165-177(13)
    private Long valorLiquidoFinal;            // col 243-255(13)
    private String statusPgto;                 // col 256-257(2)
    private String meioPagamento;              // col 260-261(2)
    private String bandeira;                   // col 262-291(30)
}
