package com.example.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddItemRequest(
        @NotNull(message = "O ID do produto é obrigatório.")
        Long productId,

        @Min(value = 1, message = "A quantidade deve ser de no mínimo 1.")
        int quantity
) {}
