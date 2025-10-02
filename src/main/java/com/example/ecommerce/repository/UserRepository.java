package com.example.ecommerce.repository;

import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca um usuário pelo seu endereço de e-mail.
     * O Spring Data JPA cria a implementação deste método automaticamente.
     * @param email O e-mail do usuário a ser buscado.
     * @return um Optional contendo o usuário, se encontrado.
     */
    Optional<User> findByEmail(String email);
}