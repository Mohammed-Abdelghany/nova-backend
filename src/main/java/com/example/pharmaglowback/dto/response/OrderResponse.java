package com.example.pharmaglowback.dto.response;

import com.example.pharmaglowback.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String customerName,
        String customerPhone,
        String governorate,
        String address,
        List<OrderItemResponse> items,
        BigDecimal subtotal,
        String couponCode,
        BigDecimal discountAmount,
        BigDecimal deliveryFee,
        BigDecimal totalPrice,
        OrderStatus status,
        LocalDateTime createdAt
) {
}
