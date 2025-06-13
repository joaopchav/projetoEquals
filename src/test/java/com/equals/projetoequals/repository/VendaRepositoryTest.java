package com.equals.projetoequals.repository;

import com.equals.projetoequals.model.Venda;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VendaRepositoryTest {

    @Autowired
    private VendaRepository repo;

    @Test
    @DisplayName("Deve salvar e buscar por bandeira")
    void testFindByBandeira() {
        // given
        Venda v = new Venda();
        v.setBandeira("VISA");
        v.setQtdParcelas("03");
        v.setDataEvento(LocalDate.now());
        repo.save(v);

        // when
        List<Venda> lista = repo.findByBandeira("VISA");

        // then
        assertThat(lista)
                .isNotEmpty()
                .allMatch(e -> "VISA".equals(e.getBandeira()));
    }
}
