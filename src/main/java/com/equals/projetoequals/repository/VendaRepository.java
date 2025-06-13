package com.equals.projetoequals.repository;

import com.equals.projetoequals.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByBandeira(String bandeira);
    List<Venda> findByQtdParcelas(String qtdParcelas);
    List<Venda> findByDataEventoBetween(LocalDate inicio, LocalDate fim);
}
