package com.algaworks.ecommerce.mapementobasico;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import com.algaworks.ecommerce.iniciandojpa.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

public class MapeandoEnumeracoesTest extends EntityManagerTest {

  @Test
  public void testarEnum() {
    Cliente cliente = new Cliente(null, "Jose Mineiro", SexoCliente.MASCULINO);

    entityManager.getTransaction().begin();
    entityManager.persist(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteVerificao = entityManager.find(Cliente.class, cliente.getId());
    assertNotNull(clienteVerificao);

  }

}
