package com.example.pharmaglowback.dto.response;

import java.math.BigDecimal;

public record DeliveryFeeResponse(
        Long id,
        String governorate,
        BigDecimal fee
) {
}
