package com.diego.ecommerce.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.ecommerce.catalog.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
