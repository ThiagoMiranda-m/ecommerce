package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddItemRequest;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody AddItemRequest addItemRequest
    ) {
        Cart updateCart = cartService.addProductToCart(
                user,
                addItemRequest.productId(),
                addItemRequest.quantity()
        );
        return ResponseEntity.ok(updateCart);
    }

    /**
     * Atualmente só vou colocar o endpoint de adicionar itens
     * No futuro irei colocar removeItem, clearCart e etc.
     */
}
