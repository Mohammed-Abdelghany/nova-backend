package com.example.pharmaglowback.service;

import com.example.pharmaglowback.dto.request.ProductRequest;
import com.example.pharmaglowback.dto.response.PageResponse;
import com.example.pharmaglowback.dto.response.ProductResponse;
import com.example.pharmaglowback.exception.ResourceNotFoundException;
import com.example.pharmaglowback.mapper.ProductMapper;
import com.example.pharmaglowback.model.Product;
import com.example.pharmaglowback.repository.OrderItemRepository;
import com.example.pharmaglowback.repository.ProductRepository;
import com.example.pharmaglowback.service.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final int BEST_SELLER_COUNT = 5;

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final CloudinaryService cloudinaryService;

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getAllProducts(
            Pageable pageable, String search, String category, BigDecimal minPrice, BigDecimal maxPrice, Boolean available) {
        var specification = ProductSpecification.filterBy(search, category, minPrice, maxPrice, available);
        Set<Long> bestSellerIds = getBestSellerIds();
        return PageResponse.from(productRepository.findAll(specification, pageable)
                .map(product -> ProductMapper.toResponse(product, bestSellerIds)));
    }

    @Transactional(readOnly = true)
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ProductMapper.toResponse(product, getBestSellerIds());
    }

    private Set<Long> getBestSellerIds() {
        return Set.copyOf(orderItemRepository.findBestSellingProductIds(PageRequest.of(0, BEST_SELLER_COUNT)));
    }

    @Transactional(readOnly = true)
    public List<String> getCategories() {
        return productRepository.findDistinctCategories();
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request, List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            throw new IllegalArgumentException("At least one product image is required");
        }

        Product product = Product.builder()
                .title(request.title())
                .description(request.description())
                .price(request.price())
                .category(request.category())
                .available(request.available() == null || request.available())
                .discountPercentage(request.discountPercentage() == null ? BigDecimal.ZERO : request.discountPercentage())
                .build();

        for (MultipartFile image : images) {
            product.addImage(cloudinaryService.uploadImage(image));
        }

        return ProductMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }
}
