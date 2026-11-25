package com.diego.ecommerce.order.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diego.ecommerce.order.dto.PedidoRequest;
import com.diego.ecommerce.order.dto.PedidoResponse;
import com.diego.ecommerce.order.service.PedidoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/* Esse método é responsável por criar e buscar um pedido

@RequestMapping("/pedidos") define o prefixo da URL.
@PostMapping cria um pedido, e @ResponseStatus(CREATED) faz a resposta ser 201.
@Valid @RequestBody converte o JSON em PedidoRequest e dispara as validações (@NotEmpty, @Min(1), e o @Valid da lista, que desce para cada item).
@GetMapping("/{id}") consulta um pedido pelo id.
Ele só fala com o PedidoService. Quem fala com o catálogo é o service, via CatalogClient, e o controller nem sabe disso.
 */
@RestController 
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService service;
    
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse criar(@Valid @RequestBody PedidoRequest req){
        return service.criar(req);
    }

    @GetMapping("/{id}")
    public PedidoResponse buscar(@PathVariable Long id){
        return service.buscar(id);
    }
}
