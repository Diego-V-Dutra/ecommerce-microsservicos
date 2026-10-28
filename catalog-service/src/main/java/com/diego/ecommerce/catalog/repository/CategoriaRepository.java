package com.diego.ecommerce.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.ecommerce.catalog.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
/*  JpaRepository é uma interface vazia do Spring Data JPA que fornece métodos prontos para realizar operações de CRUD (Create, Read, Update, Delete) em entidades do banco de dados.

    JpaRepository é parametrizado com dois tipos: a entidade que ele gerencia (Categoria) e o tipo do identificador da entidade (Long). Isso significa que o JpaRepository fornecerá métodos para gerenciar objetos do tipo Categoria, usando um identificador do tipo Long.

    o herdar dele você ganha de graça findAll(), findById(id), save(obj), deleteById(id), count() e outros.
*/ 
}
