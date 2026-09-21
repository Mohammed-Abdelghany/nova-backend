package com.example.pharmaglowback.controller;

import com.example.pharmaglowback.dto.request.DeliveryFeeUpdateRequest;
import com.example.pharmaglowback.dto.response.DeliveryFeeResponse;
import com.example.pharmaglowback.service.DeliveryFeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery-fees")
@RequiredArgsConstructor
public class DeliveryFeeController {

    private final DeliveryFeeService deliveryFeeService;

    @GetMapping
    public ResponseEntity<List<DeliveryFeeResponse>> getAllFees() {
        return ResponseEntity.ok(deliveryFeeService.getAllFees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryFeeResponse> updateFee(
            @PathVariable Long id, @Valid @RequestBody DeliveryFeeUpdateRequest request) {
        return ResponseEntity.ok(deliveryFeeService.updateFee(id, request));
    }
}
