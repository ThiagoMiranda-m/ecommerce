package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Por enquanto, não precisamos de métodos customizados aqui.
    // O JpaRepository já nos fornece tudo o que é necessário para o CRUD de produtos.
}