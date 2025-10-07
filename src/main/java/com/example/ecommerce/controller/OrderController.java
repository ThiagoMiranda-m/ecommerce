package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<Order> createOrder (@AuthenticationPrincipal User user) {
        try{
            Order order = orderService.createOrder(user);
            return ResponseEntity.ok(order);
        } catch (RuntimeException e) {
            //Retorna 400 Bad Request se algo der errado
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrderHistory(@AuthenticationPrincipal User user){
        List<Order> orders = orderService.findOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }
 }
