package com.diego.ecommerce.order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.diego.ecommerce.order.domain.Pedido;
import com.diego.ecommerce.order.domain.StatusPedido;

/* Esse método é responsável por criar uma representação de resposta do pedido, encapsulando informações relevantes como o ID do pedido, status, total e itens associados. Ele utiliza o padrão de registro (record) do Java para definir uma estrutura imutável e concisa para a resposta do pedido. 
   A função estática "de" é usada para converter um objeto Pedido em um PedidoResponse, mapeando os campos necessários e transformando a lista de itens do pedido em uma lista de respostas de itens (ItemPedidoResponse).
 */
public record PedidoResponse (
    Long id,
    StatusPedido status,
    BigDecimal total,
    List<ItemPedidoResponse> itens)
    {
    public static PedidoResponse de(Pedido pedido){
        return new PedidoResponse(
            pedido.getId(),
            pedido.getStatus(),
            pedido.getTotal(),
            pedido.getItens().stream()
                .map(ItemPedidoResponse::de)
                .toList()
        );
    }
}
