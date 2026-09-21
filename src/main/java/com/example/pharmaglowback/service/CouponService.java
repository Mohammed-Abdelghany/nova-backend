package com.example.pharmaglowback.service;

import com.example.pharmaglowback.dto.request.CouponRequest;
import com.example.pharmaglowback.dto.response.CouponResponse;
import com.example.pharmaglowback.dto.response.CouponValidationResponse;
import com.example.pharmaglowback.dto.response.PageResponse;
import com.example.pharmaglowback.exception.InvalidCouponException;
import com.example.pharmaglowback.exception.ResourceNotFoundException;
import com.example.pharmaglowback.mapper.CouponMapper;
import com.example.pharmaglowback.model.Coupon;
import com.example.pharmaglowback.model.DiscountType;
import com.example.pharmaglowback.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional(readOnly = true)
    public PageResponse<CouponResponse> getAllCoupons(Pageable pageable) {
        return PageResponse.from(couponRepository.findAll(pageable).map(CouponMapper::toResponse));
    }

    @Transactional
    public CouponResponse createCoupon(CouponRequest request) {
        String code = request.code().trim().toUpperCase();

        if (couponRepository.existsByCodeIgnoreCase(code)) {
            throw new IllegalArgumentException("Coupon code already exists: " + code);
        }
        if (request.discountType() == DiscountType.PERCENTAGE
                && request.value().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Percentage discount value cannot exceed 100");
        }

        Coupon coupon = Coupon.builder()
                .code(code)
                .discountType(request.discountType())
                .value(request.value())
                .active(true)
                .expiryDate(request.expiryDate())
                .build();

        return CouponMapper.toResponse(couponRepository.save(coupon));
    }

    @Transactional
    public void deleteCoupon(Long id) {
        if (!couponRepository.existsById(id)) {
            throw new ResourceNotFoundException("Coupon not found with id: " + id);
        }
        couponRepository.deleteById(id);
    }

    /**
     * Validates the coupon by code and returns the discount amount to apply on the given price.
     * The discount never exceeds the price itself.
     */
    @Transactional(readOnly = true)
    public BigDecimal resolveDiscount(String code, BigDecimal price) {
        Coupon coupon = couponRepository.findByCodeIgnoreCase(code.trim())
                .orElseThrow(() -> new InvalidCouponException("Coupon not found: " + code));

        if (!coupon.isActive()) {
            throw new InvalidCouponException("Coupon is no longer active: " + code);
        }
        if (coupon.getExpiryDate() != null && coupon.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidCouponException("Coupon has expired: " + code);
        }

        BigDecimal discount = coupon.getDiscountType() == DiscountType.PERCENTAGE
                ? price.multiply(coupon.getValue()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)
                : coupon.getValue();

        return discount.min(price);
    }

    @Transactional(readOnly = true)
    public CouponValidationResponse validate(String code, BigDecimal amount) {
        BigDecimal discount = resolveDiscount(code, amount);
        return new CouponValidationResponse(code.trim().toUpperCase(), discount, amount.subtract(discount));
    }
}
