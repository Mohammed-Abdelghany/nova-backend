package com.example.pharmaglowback.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Phone is required")
        String phone,

        @NotBlank(message = "Message is required")
        @Size(max = 2000, message = "Message is too long")
        String message
) {
}
