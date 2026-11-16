package com.diego.ecommerce.order.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpClientErrorException;
import lombok.RequiredArgsConstructor;

/*  @Component indica que essa classe é um componente do Spring, permitindo que seja gerenciada pelo contêiner de injeção de dependência do Spring.
 *   Contêiner de injeção de dependência é um mecanismo do Spring que gerencia a criação e a injeção de dependências entre os objetos da aplicação, facilitando o acoplamento e promovendo a reutilização de código.
 *  @RequiredArgsConstructor é uma anotação ddo Lombok que gera automaticamente um construtor com argumentos para todos os campos finais da classe, facilitando a injeção de dependências.
 *
 *  Esse método é usado para buscar informações de um produto específico no catálogo de produtos, utilizando o RestClient para fazer uma requisição HTTP GET à API do catálogo.
 */
@Component 
@RequiredArgsConstructor 
public class CatalogClient {
    private final RestClient catalogRestClient;

    public ProdutoCatalogo buscarProduto(Long id){
        try{
            return catalogRestClient.get()
                    .uri("/produtos/{id}", id)
                    .retrieve()
                    .body(ProdutoCatalogo.class);
        } catch(HttpClientErrorException.NotFound  e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto" + id + "não encontrado no catálogo");
        } catch(ResourceAccessException e){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Catálogo indisponível");
        }
    }
}
