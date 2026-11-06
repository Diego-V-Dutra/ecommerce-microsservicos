package com.diego.ecommerce.catalog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diego.ecommerce.catalog.dto.ProdutoRequest;
import com.diego.ecommerce.catalog.dto.ProdutoResponse;
import com.diego.ecommerce.catalog.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/* O RestController é uma anotação que combina @Controller e @ResponseBody, indicando que a classe é um controlador REST e que os métodos retornam dados diretamente no corpo da resposta HTTP.
Ele marca a classe como um controller de API: os métodos devolvem dados (objetos Java), e o Spring os converte em JSON automaticamente.

O RequestMapping é uma anotação que mapeia as requisições HTTP para métodos específicos do controlador. Neste caso, todas as requisições para o caminho "/produtos" serão direcionadas para os métodos desta classe.
Ele define o prefixo da URL. Todos os endpoints da classe começam com /produtos.
 */
@RestController 
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;

    /* O método listar é responsável por lidar com requisições HTTP GET para listar os produtos cadastrados no sistema, podendo filtrar por categoria. Ele recebe como parâmetros o ID da categoria (categoriaId) e um objeto Pageable (pageable) que contém informações sobre paginação e ordenação.
       
    O GET é responsável por buscar informações do servidor. Ele é usado para recuperar dados de um recurso específico, sem alterar o estado do recurso no servidor.  
    */
    @GetMapping
    public Page<ProdutoResponse> listar(@RequestParam(required = false) Long categoriaId,
                                         Pageable pageable){
        return service.listar(categoriaId, pageable);
    }
    
    /* O método buscar é responsável por lidar com requisições HTTP GET para buscar um produto pelo seu ID. Ele recebe como parâmetro o ID do produto (id) e retorna um objeto ProdutoResponse correspondente ao produto encontrado.
     */
    @GetMapping("/{id}")
    public ProdutoResponse buscar(@PathVariable Long id){
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(@Valid @RequestBody ProdutoRequest req){
        return service.criar(req);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest req){
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void desativar(@PathVariable Long id){
        service.desativar(id);
    }
}
