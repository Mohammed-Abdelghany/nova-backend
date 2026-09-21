package com.example.pharmaglowback.repository;

import com.example.pharmaglowback.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
