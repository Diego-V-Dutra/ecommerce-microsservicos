package com.diego.ecommerce.order.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Pedido
 */

@Entity 
@Getter 
@Setter 
@NoArgsConstructor 
public class Pedido {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* @Enumerated indica que o campo status é um tipo enumerado, e o EnumType.STRING especifica que o valor do enum será armazenado como uma string no banco de dados.
        @JdbcTypeCode é uma anotação específica do Hibernate que permite definir o tipo de dados JDBC a ser usado para mapear o campo status. Neste caso, SqlTypes.
        VARCHAR indica que o valor do enum será armazenado como uma string no banco de dados.
     */
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "status", nullable = false, length = 20)
    private StatusPedido status = StatusPedido.PENDENTE;

    @Column(name = "total",nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    /* @OneToMany mapeia a relação de um para muitos entre Pedido e ItemPedido.
    * MappedBy = "pedido" indica que a propriedade pedido na entidade ItemPedido é a responsável pelo mapeamento da relação.
    * CascadeType.ALL significa que todas as operações de persistência (salvar, atualizar, excluir) realizadas em Pedido serão propagadas para os itens associados.
    * List<ItemPedido> itens = new ArrayList<>() inicializa a lista de itens como uma lista vazia, garantindo que não ocorra NullPointerException ao adicionar itens posteriormente.
     */
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    /* Esse método adicionarItem é responsável por adicionar um item à lista de itens do pedido. 
    Ele recebe um objeto ItemPedido como parâmetro, define o pedido associado ao item como o próprio pedido (this) e adiciona o item à lista de itens.
     */
    public void adicionarItem(ItemPedido item){
        item.setPedido(this);
        itens.add(item);
    }
}
