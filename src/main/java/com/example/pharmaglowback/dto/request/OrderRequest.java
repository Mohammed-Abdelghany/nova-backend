package com.example.pharmaglowback.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequest(

        @NotBlank(message = "Customer name is required")
        String customerName,

        @NotBlank(message = "Customer phone is required")
        String customerPhone,

        @NotBlank(message = "Governorate is required")
        String governorate,

        @NotBlank(message = "Address is required")
        String address,

        @NotEmpty(message = "Cart must contain at least one item")
        @Valid
        List<OrderItemRequest> items,

        String couponCode
) {
}
