package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long>{
    /**
     * Busca um carrinho de compras associado a um ID de usuário específico.
     * @param userId O ID do usuário proprietário do carrinho.
     * @return um Optional contendo o carrinho, se encontrado.
     */
    Optional<Cart> findByUserId(Long userId);
}
