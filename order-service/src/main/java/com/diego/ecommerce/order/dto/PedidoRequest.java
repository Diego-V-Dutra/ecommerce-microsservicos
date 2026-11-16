package com.diego.ecommerce.order.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

/* Esse método é usado para representar um pedido, contendo uma lista de itens de pedido.
* Ele utiliza anotações de validação para garantir que a lista de itens não estaja vazia e que cada item seja válido, ou seja, que o ID do produto não seja nulo e que a quantidade seja maior ou igual a 1.
 */
public record PedidoRequest(
    @NotEmpty @Valid List<ItemPedidoRequest> itens
) {
}
