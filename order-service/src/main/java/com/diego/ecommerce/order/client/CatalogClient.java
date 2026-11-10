package com.diego.ecommerce.order.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpClientErrorException;
import lombok.RequiredArgsConstructor;

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
