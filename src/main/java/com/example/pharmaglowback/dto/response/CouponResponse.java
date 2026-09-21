package com.example.pharmaglowback.dto.response;

import com.example.pharmaglowback.model.DiscountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CouponResponse(
        Long id,
        String code,
        DiscountType discountType,
        BigDecimal value,
        boolean active,
        LocalDateTime expiryDate,
        LocalDateTime createdAt
) {
}
