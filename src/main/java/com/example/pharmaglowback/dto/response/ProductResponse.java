package com.example.pharmaglowback.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductResponse(
        Long id,
        String title,
        String description,
        BigDecimal price,
        BigDecimal discountPercentage,
        BigDecimal finalPrice,
        String imageUrl,
        List<String> images,
        String category,
        boolean available,
        boolean bestSeller,
        LocalDateTime createdAt
) {
}
