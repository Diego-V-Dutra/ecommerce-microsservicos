package com.diego.ecommerce.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diego.ecommerce.order.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
}
