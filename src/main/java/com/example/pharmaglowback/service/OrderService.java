package com.example.pharmaglowback.service;

import com.example.pharmaglowback.dto.request.OrderItemRequest;
import com.example.pharmaglowback.dto.request.OrderRequest;
import com.example.pharmaglowback.dto.response.OrderResponse;
import com.example.pharmaglowback.dto.response.PageResponse;
import com.example.pharmaglowback.exception.ResourceNotFoundException;
import com.example.pharmaglowback.mapper.OrderMapper;
import com.example.pharmaglowback.model.Order;
import com.example.pharmaglowback.model.OrderItem;
import com.example.pharmaglowback.model.OrderStatus;
import com.example.pharmaglowback.model.Product;
import com.example.pharmaglowback.repository.OrderRepository;
import com.example.pharmaglowback.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CouponService couponService;
    private final DeliveryFeeService deliveryFeeService;
    private final TelegramNotificationService telegramNotificationService;

    @Transactional(readOnly = true)
    public PageResponse<OrderResponse> getAllOrders(Pageable pageable) {
        return PageResponse.from(orderRepository.findAll(pageable).map(OrderMapper::toResponse));
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .customerName(request.customerName())
                .customerPhone(request.customerPhone())
                .governorate(request.governorate())
                .address(request.address())
                .status(OrderStatus.PENDING)
                .build();

        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + itemRequest.productId()));

            if (!product.isAvailable()) {
                throw new IllegalArgumentException("Product is not available: " + product.getTitle());
            }

            BigDecimal unitPrice = product.getFinalPrice();
            BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(itemRequest.quantity()));
            subtotal = subtotal.add(lineTotal);

            order.addItem(OrderItem.builder()
                    .productId(product.getId())
                    .productTitle(product.getTitle())
                    .unitPrice(unitPrice)
                    .quantity(itemRequest.quantity())
                    .lineTotal(lineTotal)
                    .build());
        }

        String couponCode = null;
        BigDecimal discountAmount = BigDecimal.ZERO;

        if (StringUtils.hasText(request.couponCode())) {
            couponCode = request.couponCode().trim().toUpperCase();
            discountAmount = couponService.resolveDiscount(couponCode, subtotal);
        }

        BigDecimal deliveryFee = deliveryFeeService.resolveFee(request.governorate());

        order.setSubtotal(subtotal);
        order.setCouponCode(couponCode);
        order.setDiscountAmount(discountAmount);
        order.setDeliveryFee(deliveryFee);
        order.setTotalPrice(subtotal.subtract(discountAmount).add(deliveryFee));

        Order saved = orderRepository.save(order);
        OrderResponse response = OrderMapper.toResponse(saved);

        telegramNotificationService.notifyNewOrder(response);

        return response;
    }
}
