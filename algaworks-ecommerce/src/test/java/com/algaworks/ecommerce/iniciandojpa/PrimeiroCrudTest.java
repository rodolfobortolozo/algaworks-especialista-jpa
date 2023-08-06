package com.algaworks.ecommerce.iniciandojpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.algaworks.ecommerce.model.Cliente;

public class PrimeiroCrudTest extends EntityManagerTest {

  @Test
  @Order(1)
  public void cadastrarCliente() {

    Cliente cliente1 = new Cliente(1, "Alessandra");
    Cliente cliente2 = new Cliente(2, "Rodolfo");

    entityManager.getTransaction().begin();
    entityManager.persist(cliente1);
    entityManager.persist(cliente2);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteSalvo1 = entityManager.find(Cliente.class, 1);
    Cliente clienteSalvo2 = entityManager.find(Cliente.class, 2);

    assertNotNull(clienteSalvo1);
    assertNotNull(clienteSalvo2);

  }

  @Test
  @Order(2)
  public void atualizaCliente() {
    Cliente cliente1 = entityManager.find(Cliente.class, 1);
    Cliente cliente2 = entityManager.find(Cliente.class, 2);

    cliente1.setNome("Alessandra Zanardi");
    cliente2.setNome("Rodolfo Bortolozo");

    entityManager.getTransaction().begin();
    entityManager.merge(cliente1);
    entityManager.merge(cliente2);
    entityManager.getTransaction().commit();

    Cliente clienteSalvo1 = entityManager.find(Cliente.class, 1);
    Cliente clienteSalvo2 = entityManager.find(Cliente.class, 2);

    assertEquals("Alessandra Zanardi", clienteSalvo1.getNome());
    assertEquals("Rodolfo Bortolozo", clienteSalvo2.getNome());

  }

  @Test
  @Order(3)
  public void deletarCliente() {

    Cliente cliente2 = entityManager.find(Cliente.class, 2);

    entityManager.getTransaction().begin();
    entityManager.remove(cliente2);
    entityManager.getTransaction().commit();

  }

}
