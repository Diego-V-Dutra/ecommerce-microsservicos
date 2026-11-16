package com.diego.ecommerce.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/* Esse método é usado para representar um item de pedido, contendo o ID do produto e a quantidade solicitada. 
 * Ele utiliza anotações de validação para garantir que o ID do produto não seja nulo e que a quantidade seja maior ou igual a 1.
 */
public record ItemPedidoRequest(
    @NotNull Long produtoId,
    @NotNull @Min(1) Integer quantidade
) {    
}
