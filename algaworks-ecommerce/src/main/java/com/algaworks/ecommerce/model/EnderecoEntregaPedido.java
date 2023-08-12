package com.algaworks.ecommerce.model;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EnderecoEntregaPedido {

  private String cep;

  private String logradouro;

  private String numero;

  private String complemento;

  private String bairro;

  private String cidade;

  private String estado;

}
