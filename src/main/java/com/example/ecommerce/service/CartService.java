package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    /**
     * Adiciona um produto ao carrinho do usuário.
     * Se o item já existir no carrinho, apenas incrementa a quantidade.
     *
     * @param user      O usuário autenticado.
     * @param productId O ID do produto a ser adicionado.
     * @param quantity  A quantidade a ser adicionada.
     * @return O carrinho atualizado.
     */

    @Transactional
    public Cart addProductToCart(User user, Long productId, int quantity){
        //1. Encontra o carrinho do usuário logado ou cria um novo se não existir
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        //2. Busca o produto no database
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + productId));

        //3. Verifica se o item tá no carrinho
        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null){
            //Se já tem o item, vai aumentar a quantidade.
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            //Se for novo, cria e adiciona no carrinho.
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        return cartRepository.save(cart);
    }

    //No futuro, vou adicionar métodos como "removeProductFromCart", "updateQuantity" e etc.

}
