package com.algaworks.ecommerce.mapementobasico;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import com.algaworks.ecommerce.iniciandojpa.EntityManagerTest;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

  @Test
  public void analizarMapeamentoObjetoEmbutido() {

    EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
    endereco.setCep("15700-000");
    endereco.setBairro("Centro");
    endereco.setCidade("Fernandópolis");
    endereco.setComplemento("Apto");
    endereco.setEstado("SP");
    endereco.setLogradouro("RUA PERNANBUCO");
    endereco.setNumero("2222");

    Pedido pedido = new Pedido();
    pedido.setDataPedido(LocalDateTime.now());
    pedido.setStatusPedido(StatusPedido.AGUARDANDO);
    pedido.setTotal(BigDecimal.valueOf(1000));
    pedido.setEnderecoEntrega(endereco);

    entityManager.getTransaction().begin();
    entityManager.persist(pedido);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Pedido pedidoverificacao = entityManager.find(Pedido.class, pedido.getId());
    assertNotNull(pedidoverificacao);
    assertNotNull(pedidoverificacao.getEnderecoEntrega());
    assertNotNull(pedidoverificacao.getEnderecoEntrega().getCep());
  }

}
