package com.diego.ecommerce.order.dto;

import java.math.BigDecimal;

import com.diego.ecommerce.order.domain.ItemPedido;

/* Esse método é responsável por criar uma representação de resposta do item do pedido, encapsulando informações relevantes como o ID do produto, quantidade e preço unitário. 
    Ele utiliza o padrão de registro (record) do Java para definir uma estrutura imutável e concisa para a resposta do item do pedido.
    a função estática "de" é usada para converter um objeto ItemPedido em um ItemPedidoResponse, mapeando os campos necessários.
    */
public record ItemPedidoResponse(
    Long produtoId,
    Integer quantidade,
    BigDecimal precoUnitario
) {
    public static ItemPedidoResponse de(ItemPedido item){
        return new ItemPedidoResponse(
            item.getProdutoId(),
            item.getQuantidade(), 
            item.getPrecoUnitario());
    }
}
