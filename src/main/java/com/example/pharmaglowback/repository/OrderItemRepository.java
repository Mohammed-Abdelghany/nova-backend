package com.example.pharmaglowback.repository;

import com.example.pharmaglowback.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select oi.productId from OrderItem oi group by oi.productId order by sum(oi.quantity) desc")
    List<Long> findBestSellingProductIds(Pageable pageable);
}
