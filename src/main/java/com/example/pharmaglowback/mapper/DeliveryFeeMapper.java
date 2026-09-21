package com.example.pharmaglowback.mapper;

import com.example.pharmaglowback.dto.response.DeliveryFeeResponse;
import com.example.pharmaglowback.model.DeliveryFee;

public final class DeliveryFeeMapper {

    private DeliveryFeeMapper() {
    }

    public static DeliveryFeeResponse toResponse(DeliveryFee fee) {
        return new DeliveryFeeResponse(fee.getId(), fee.getGovernorate(), fee.getFee());
    }
}
