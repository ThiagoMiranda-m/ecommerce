package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    /**
     * Cria um novo pedido a partir do carrinho
     * @param user O usuário que está finalizando a compra
     * @return O pedido criado
     */
    @Transactional
    public Order createOrder(User user) {
        //1.Encontra o carrinho do usuário
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado para o usuário."));

        if (cart.getItems().isEmpty()){
            throw new RuntimeException("Não é possível criar um pedido deum carrinho");
        }

        //.Cria a entidade Pedido (Order)
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        BigDecimal totalPrice = BigDecimal.ZERO;

        //3. Criação dos OrderItems via itens do carrinho
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            //Verifica se tem estoque
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
            }

            //Cria o item do pedido
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice()); // salva o preço na hora da compra

            order.getItems().add(orderItem);

            //Atualiza o estoque do produto
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            //Calculando o preço total
            totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        }

        order.setTotalPrice(totalPrice);

        //4.Limpa o carrinho após criar o pedido
        cart.getItems().clear();
        cartRepository.save(cart);

        //5. Salva e retorna o pedido
        return orderRepository.save(order);
    }

    /**
     * Busca o histórico de pedidos
     * @param user O usuário que os pedidos serão buscados
     * @return Uma lista de pedidos
     */
    public List<Order> findOrdersByUser(User user){
        return orderRepository.findByUserId(user.getId());
    }
}
