package com.example.ecommerce.repository;

import com.example.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, Long> {
    /**
     * Busca um carrinho de compras associado a um ID de usuário específico.
     * @param userId O ID do usuário proprietário do carrinho.
     * @return um Optional contendo o carrinho, se encontrado.
     */
    List<Order> findByUserId(Long userId);
}
