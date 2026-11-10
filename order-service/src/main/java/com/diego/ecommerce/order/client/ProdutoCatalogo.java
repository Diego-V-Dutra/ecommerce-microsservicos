package com.diego.ecommerce.order.client;

import java.math.BigDecimal;

/* record é uma classe imutável que representa um produto no catálogo com seus atributos.
    Esse método é usado para mapear a resposta da API do catálogo de produtos para um objeto Java, facilitando o acesso aos dados do produto.
 */
public record ProdutoCatalogo(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    Integer estoque,
    Boolean ativo,
    Long categoriaId,
    String categoriaNome
) {
    
}
