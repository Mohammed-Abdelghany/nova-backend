package com.example.pharmaglowback.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DeliveryFeeUpdateRequest(

        @NotNull(message = "Fee is required")
        @DecimalMin(value = "0.0", message = "Fee cannot be negative")
        BigDecimal fee
) {
}
