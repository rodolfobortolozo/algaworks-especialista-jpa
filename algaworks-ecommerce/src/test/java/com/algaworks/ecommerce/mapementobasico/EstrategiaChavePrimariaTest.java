package com.algaworks.ecommerce.mapementobasico;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import com.algaworks.ecommerce.iniciandojpa.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

  @Test
  public void testarEstrategiaChave() {

    Categoria categoria = new Categoria();
    categoria.setNome("Eletrônicos");

    entityManager.getTransaction().begin();
    entityManager.persist(categoria);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Categoria CategoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());

    assertNotNull(CategoriaVerificacao);

  }

}
