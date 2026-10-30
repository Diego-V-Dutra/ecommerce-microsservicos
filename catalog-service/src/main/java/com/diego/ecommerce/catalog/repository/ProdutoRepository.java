package com.diego.ecommerce.catalog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.ecommerce.catalog.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    /*
    * Esse método é responsável por buscar todos os produtos ativos no banco de dados, retornando-os em uma página (Page) de resultados. 
    * Ele recebe um objeto Pageable como parâmetro, que contém informações sobre a página solicitada (como número da página, tamanho da página e ordenação). 
    * O método retorna uma Page contendo os produtos ativos encontrados. 
    */
    Page<Produto> findByAtivoTrue(Pageable pageable);
    /*
    * Esse método é responsável por buscar todos os produtos ativos de uma determinada categoria no banco de dados, retornando-os em uma página (Page) de resultados.
    * Ele recebe dois parâmetros: o ID da categoria (categoriaId) e um objeto Pageable.
    */
    Page<Produto> findByAtivoTrueAndCategoriaId(Long categoriaId, Pageable pageable);
}
