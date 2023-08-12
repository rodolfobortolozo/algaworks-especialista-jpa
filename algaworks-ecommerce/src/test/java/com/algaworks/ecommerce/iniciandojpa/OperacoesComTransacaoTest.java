package com.algaworks.ecommerce.iniciandojpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {

  @Test
  @DisplayName("Inserir Registro")
  public void inserirPrimeiroObjeto() {
    Produto produto =
        new Produto(null, "Camera Samsung", "Melhor Camera do Mercado", BigDecimal.valueOf(1000));

    entityManager.getTransaction().begin();
    entityManager.persist(produto);
    entityManager.getTransaction().commit();

    entityManager.clear(); // Limpa a memória para forçar a consulta no banco de dados

    Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
    assertNotNull(produtoVerificacao);

  }

  @Test
  @DisplayName("Remover Registro")
  public void removerObjeto() {

    Produto produto = entityManager.find(Produto.class, 3);

    entityManager.getTransaction().begin();
    entityManager.remove(produto);
    entityManager.getTransaction().commit();

    Produto produtoVerificacao = entityManager.find(Produto.class, 3);
    assertNull(produtoVerificacao);
  }

  @Test
  @DisplayName("Atualizar Registro")
  public void atualizarObjeto() {

    Produto produto =
        new Produto(null, "Kindle PaperWhite", "Conheça o novo Kindle", BigDecimal.valueOf(123456));

    entityManager.getTransaction().begin();
    Produto produtosalvo = entityManager.merge(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, produtosalvo.getId());
    assertNotNull(produtoVerificacao);
    assertEquals(produtoVerificacao.getNome(), produto.getNome());

  }

  @Test
  @DisplayName("Atualizar Registro Gerenciado")
  public void atualizarObjetoGerenciado() {

    Produto produto = entityManager.find(Produto.class, 1);
    produto.setNome("Kindle PaperWhite");

    entityManager.getTransaction().begin();
    entityManager.merge(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, 1);

    assertEquals(produtoVerificacao, produto);

  }

  @Test
  @DisplayName("Inserir Registro com Merge")
  public void inserirObjetocomMerge() {
    Produto produto = new Produto();

    produto.setNome("Microfone");
    produto.setDescricao("Microfone de Capela");
    produto.setPreco(BigDecimal.valueOf(12));

    entityManager.getTransaction().begin();
    Produto produtoSalvo = entityManager.merge(produto);
    entityManager.getTransaction().commit();

    entityManager.clear(); // Limpa a memória para forçar a consulta no banco de dados

    Produto produtoVerificacao = entityManager.find(Produto.class, produtoSalvo.getId());
    assertNotNull(produtoVerificacao);

  }

  @Test
  @DisplayName("Impedir atualização detach")
  public void impedirOperacaoComBD() {

    Produto produto = entityManager.find(Produto.class, 1);
    entityManager.detach(produto);

    entityManager.getTransaction().begin();
    produto.setNome("Kindle Detach");
    entityManager.merge(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Produto produtoVerificacao = entityManager.find(Produto.class, 1);

    assertEquals(produtoVerificacao, produto);

  }

  @Test
  @DisplayName("Abrir e Fechar Transações")
  public void abrirFecharAtransacao() {

    Produto produto = new Produto();

    entityManager.getTransaction().begin();


    entityManager.persist(produto);
    entityManager.merge(produto);
    entityManager.remove(produto);

    entityManager.getTransaction().commit();
  }

}
