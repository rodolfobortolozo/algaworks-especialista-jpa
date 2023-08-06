package com.algaworks.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntendendoKJUnitTest {
	
	@BeforeClass
	public static void iniciarTestes() {
		System.out.println("Iniciar Testes");
	}
	
	@BeforeClass
	public static void encerrarTestes() {
		System.out.println("Encerrar Testes");
	}
	
	@Before
	public void iniciarTeste() {
		System.out.println("Antes de cada Teste");
	}
	
	@After
	public void encerrarTeste() {
		System.out.println("Depois de cada Teste");
	}
	
	@Test
	public void testandoAlgo() {
		String nome = String.format("%s", "Rodolfo");
		
		Assert.assertEquals("Rodolfo R B", nome);
	}
	
	@Test
	public void testandoOutraCoisa() {
		System.out.println("Testando Outra Coisa");
		
	}
	

}
