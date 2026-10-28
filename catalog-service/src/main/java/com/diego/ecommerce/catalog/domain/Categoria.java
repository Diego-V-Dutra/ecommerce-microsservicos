package com.diego.ecommerce.catalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//O entity mostra ao JPA que essa classe é uma entidade e deve ser mapeada para uma tabela no banco de dados
@Entity 
//geram automaticamente os métodos getters e setters para os atributos da classe (getId(), setId(), getNome() e setNome())
@Getter 
@Setter
//gera automaticamente um construtor vazio para a classe, que é necessário para o JPA criar instâncias da entidade com o AUTO_INCREMENT 
@NoArgsConstructor 
public class Categoria {
    @Id 
    //diz que quem gera o id da entidade é o banco de dados, e não a aplicação
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //descreve a coluna. Isso precisa bater com a migration (VARCHAR(100) NOT NULL UNIQUE), porque o ddl-auto: validate confere os dois lados.
    @Column(nullable = false, unique = true, length = 100)
    private String nome;
}
