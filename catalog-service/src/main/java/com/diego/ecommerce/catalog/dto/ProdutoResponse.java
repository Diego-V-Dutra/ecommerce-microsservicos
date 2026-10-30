package com.diego.ecommerce.catalog.dto;

import java.math.BigDecimal;

import com.diego.ecommerce.catalog.domain.Produto;

/*
 * O Response é uma classe que representa a resposta de um produto no sistema.
 * Ela é utilizada para retornar os dados do produto ao cliente, após uma requisição.
 */
public record ProdutoResponse(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    Integer estoque,
    Boolean ativo,
    Long categoriaId,
    String categoriaNome
) {
    /*
    Esse método estático é responsável por converter um objeto do tipo Produto em um objeto do tipo ProdutoResponse. Ele recebe um objeto Produto como parâmetro e retorna um novo objeto ProdutoResponse com os dados do produto.

    Static methods são métodos que pertencem à classe e não a uma instância específica da classe. Eles podem ser chamados sem a necessidade de criar um objeto da classe. No caso do método de, ele é utilizado para criar um objeto ProdutoResponse a partir de um objeto Produto, facilitando a conversão entre os dois tipos de objetos.
    */
    public static ProdutoResponse de(Produto p){
        return new ProdutoResponse(
            p.getId(),
            p.getNome(),
            p.getDescricao(),
            p.getPreco(),
            p.getEstoque(),
            p.getAtivo(),
            p.getCategoria().getId(),
            p.getCategoria().getNome());  
    }
    
}
