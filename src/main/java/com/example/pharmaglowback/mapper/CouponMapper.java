package com.example.pharmaglowback.mapper;

import com.example.pharmaglowback.dto.response.CouponResponse;
import com.example.pharmaglowback.model.Coupon;

public final class CouponMapper {

    private CouponMapper() {
    }

    public static CouponResponse toResponse(Coupon coupon) {
        return new CouponResponse(
                coupon.getId(),
                coupon.getCode(),
                coupon.getDiscountType(),
                coupon.getValue(),
                coupon.isActive(),
                coupon.getExpiryDate(),
                coupon.getCreatedAt()
        );
    }
}
