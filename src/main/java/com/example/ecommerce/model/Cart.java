package com.example.ecommerce.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento One-to-One: Um carrinho pertence a um único usuário.
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relacionamento One-to-Many: Um carrinho pra vários itens
    // Cascade.ALL: Carrinho deletado, todos os itens também.
    // orphanRemoval = true: Se item excluído da lista, deletará do banco também.
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
}
