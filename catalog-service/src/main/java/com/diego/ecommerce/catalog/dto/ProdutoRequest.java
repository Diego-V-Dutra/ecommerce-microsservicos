package com.diego.ecommerce.catalog.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
/*
* O Request é uma classe que representa a requisição de criação de um produto. 
* Ela é o arquivo de entrada para a criação de um produto no sistema. O request é responsável por receber os dados do produto que serão enviados pelo cliente, e validá-los antes de serem processados pelo sistema.
*
* Record é um tipo do Java para classes que só guardam dados. Você declara os campos entre parênteses e o Java gera sozinho o construtor (Nesse caso, apenas os setters, por causa do tipo de arquivo(entrada-Request)), os métodos sao imutáveis*/
public record ProdutoRequest(
    @NotBlank @Size(max = 150) String nome,
    @Size(max = 500) String descricao,
    @NotNull  @DecimalMin("0.01") BigDecimal preco,
    @NotNull @Min(0) Integer quantidade,
    @NotNull Long categoriaId){
}


