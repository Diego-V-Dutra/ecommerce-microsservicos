package com.diego.ecommerce.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


/* @Configuration é responsável por configurar o cliente REST do catálogo, 
 * Ele define a URL base do serviço de catálogo a partir da prapriedade "catalog.url" no arquivo application.yml.
 */
@Configuration 
public class CatalogClientConfig {

    /* @Bean indica que o método catalogRestClient() cria um bean gerenciado pelo Spring, que é o cliente REST do catálogo. 
        Ele utiliza a URL base do serviço de catálogo para criar uma instância do RestClient, que será usada para fazer chamadas HTTP ao serviço de catálogo.
     */
    @Bean

    /*O método catalogRestClient() recebe a URL do serviço de catálogo como parâmetro, que é injetada a partir da propriedade "catalog.url" no arquivo application.yml.
        ele retorna uma instância do RestClient configurada com a URL base do serviço de catálogo, permitindo que o Order Service faça chamadas HTTP para o serviço de catálogo.
    */
    public RestClient catalogRestClient(@Value("${catalog.url}") String catalogUrl) {
        return RestClient.builder()
                .baseUrl(catalogUrl)
                .build();
    }
}
