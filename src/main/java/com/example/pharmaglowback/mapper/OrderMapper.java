package com.example.pharmaglowback.mapper;

import com.example.pharmaglowback.dto.response.OrderItemResponse;
import com.example.pharmaglowback.dto.response.OrderResponse;
import com.example.pharmaglowback.model.Order;
import com.example.pharmaglowback.model.OrderItem;

public final class OrderMapper {

    private OrderMapper() {
    }

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                order.getCustomerPhone(),
                order.getGovernorate(),
                order.getAddress(),
                order.getItems().stream().map(OrderMapper::toItemResponse).toList(),
                order.getSubtotal(),
                order.getCouponCode(),
                order.getDiscountAmount(),
                order.getDeliveryFee(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

    private static OrderItemResponse toItemResponse(OrderItem item) {
        return new OrderItemResponse(
                item.getProductId(),
                item.getProductTitle(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getLineTotal()
        );
    }
}
