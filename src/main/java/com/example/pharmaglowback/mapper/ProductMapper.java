package com.example.pharmaglowback.mapper;

import com.example.pharmaglowback.dto.response.ProductResponse;
import com.example.pharmaglowback.model.Product;
import com.example.pharmaglowback.model.ProductImage;

import java.util.Set;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static ProductResponse toResponse(Product product) {
        return toResponse(product, Set.of());
    }

    public static ProductResponse toResponse(Product product, Set<Long> bestSellerIds) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getDiscountPercentage(),
                product.getFinalPrice(),
                product.getCoverImageUrl(),
                product.getImages().stream().map(ProductImage::getImageUrl).toList(),
                product.getCategory(),
                product.isAvailable(),
                bestSellerIds.contains(product.getId()),
                product.getCreatedAt()
        );
    }
}
