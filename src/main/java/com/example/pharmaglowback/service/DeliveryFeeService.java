package com.example.pharmaglowback.service;

import com.example.pharmaglowback.dto.request.DeliveryFeeUpdateRequest;
import com.example.pharmaglowback.dto.response.DeliveryFeeResponse;
import com.example.pharmaglowback.exception.ResourceNotFoundException;
import com.example.pharmaglowback.mapper.DeliveryFeeMapper;
import com.example.pharmaglowback.model.DeliveryFee;
import com.example.pharmaglowback.repository.DeliveryFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryFeeService {

    private final DeliveryFeeRepository deliveryFeeRepository;

    @Transactional(readOnly = true)
    public List<DeliveryFeeResponse> getAllFees() {
        return deliveryFeeRepository.findAll().stream()
                .sorted(Comparator.comparing(DeliveryFee::getGovernorate))
                .map(DeliveryFeeMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BigDecimal resolveFee(String governorate) {
        return deliveryFeeRepository.findByGovernorateIgnoreCase(governorate)
                .map(DeliveryFee::getFee)
                .orElse(BigDecimal.ZERO);
    }

    @Transactional
    public DeliveryFeeResponse updateFee(Long id, DeliveryFeeUpdateRequest request) {
        DeliveryFee fee = deliveryFeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery fee not found with id: " + id));
        fee.setFee(request.fee());
        return DeliveryFeeMapper.toResponse(deliveryFeeRepository.save(fee));
    }
}
