package com.diego.ecommerce.order.service;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.diego.ecommerce.order.client.CatalogClient;
import com.diego.ecommerce.order.client.ProdutoCatalogo;
import com.diego.ecommerce.order.domain.ItemPedido;
import com.diego.ecommerce.order.domain.Pedido;
import com.diego.ecommerce.order.dto.ItemPedidoRequest;
import com.diego.ecommerce.order.dto.PedidoRequest;
import com.diego.ecommerce.order.dto.PedidoResponse;
import com.diego.ecommerce.order.repository.PedidoRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class PedidoService {
    private final PedidoRepository pedidos;
    private final CatalogClient catalogo;

    /*Esse método é responsável por criar um pedido vazio com o valor padrão pedente e começa o total em zero
     Para cada item do pedido, pergunta ao catálogo pelo produto(catalogo.buscarProduto). Se o produto não existir, ele lança o erro 400, e se estiver o catálogo estiver fora do ar, o 503
     Valida duas regras de negócio: o produto precisa estar ativo e estoque suficiente
     Monta o Pedido copiando preço do catálogo (produto.preco()). É o preço congelado no momento da compra.
     Soma ao total: preço × quantidade. BigDecimal não usa + nem *, e sim os métodos add e multiply, que fazem aritmética exata (sem os erros de arredondamento do double). BigDecimal.valueOf(...) converte a quantidade para esse tipo.
     Boolean.TRUE.equals(produto.ativo()) é uma forma segura de testar um Boolean que pode vir null: se vier null, dá false em vez de estourar um NullPointerException.
     Define o total e salva. Por causa do cascade, o save do pedido grava os itens junto.
     */

    @Transactional 
    public PedidoResponse criar(PedidoRequest req){
        Pedido pedido = new Pedido();
        BigDecimal total = BigDecimal.ZERO;

        for(ItemPedidoRequest itemReq : req.itens()){
            ProdutoCatalogo produto = catalogo.buscarProduto(itemReq.produtoId());

            if(!Boolean.TRUE.equals(produto.ativo())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto" + produto.id() + "não está disponível");
            }
            
            if(produto.estoque() < itemReq.quantidade()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto" + produto.id());
            }
            
            ItemPedido item = new ItemPedido();
            item.setProdutoId(produto.id());
            item.setQuantidade(itemReq.quantidade());
            item.setPrecoUnitario(produto.preco());
            pedido.adicionarItem(item);

            total = total.add(produto.preco().multiply(BigDecimal.valueOf(itemReq.quantidade())));
        }
        
        pedido.setTotal(total);
        return PedidoResponse.de(pedidos.save(pedido));
    }

    @Transactional(readOnly = true)
    public PedidoResponse buscar(Long id){
        Pedido pedido = pedidos.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
        return PedidoResponse.de(pedido);
    }
    /*A transação aqui é necessária porque getItens() é carregado sob demanda (LAZY): sem a transação aberta, o acesso à lista daria LazyInitializationException.

        Dois pontos para ficarem anotados:
    1 - O estoque não é baixado. Hoje o pedido só confere o estoque, mas o catálogo continua mostrando o mesmo número depois da compra. Para baixar, o catálogo precisa de um endpoint novo (algo como "reservar estoque"), e é o tipo de coisa que complica microsserviços: se o pedido for salvo e a baixa falhar, os dois bancos ficam inconsistentes. Voltamos a isso quando o pagamento entrar.
    2 -A chamada HTTP acontece dentro da transação, então a conexão com o banco fica ocupada enquanto o catálogo responde. Para um projeto de estudo está bem, mas em produção costuma-se consultar o catálogo antes de abrir a transação.
     */
}
