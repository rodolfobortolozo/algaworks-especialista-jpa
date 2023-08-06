package com.algaworks.ecommerce.iniciandojpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.algaworks.ecommerce.model.Produto;

public class ConsultaRegistrosTest extends EntityManagerTest {

  @Test
  @DisplayName("Buscar por Id")
  public void buscarPorIdentificador() {
    Produto produto = entityManager.find(Produto.class, 1);

    assertNotNull(produto);
    assertEquals("Kindle", produto.getNome());
  }

  @Test
  @DisplayName("Atualiza a referencia da entidade")
  public void atualizarAReferencia() {
    Produto produto = entityManager.find(Produto.class, 1);
    produto.setNome("Microfone Samsung");

    // Busca no banco de novo os dados da entidade
    entityManager.refresh(produto);

    assertEquals("Kindle", produto.getNome());
  }


}
