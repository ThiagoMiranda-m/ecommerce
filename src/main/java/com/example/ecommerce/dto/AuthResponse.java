package com.example.ecommerce.dto;

import lombok.Builder;

@Builder
public record AuthResponse(String token) {}