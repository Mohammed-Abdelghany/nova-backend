package com.example.pharmaglowback.dto.response;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long productId,
        String productTitle,
        BigDecimal unitPrice,
        int quantity,
        BigDecimal lineTotal
) {
}
