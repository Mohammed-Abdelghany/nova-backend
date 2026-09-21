package com.example.pharmaglowback.repository;

import com.example.pharmaglowback.model.DeliveryFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryFeeRepository extends JpaRepository<DeliveryFee, Long> {

    Optional<DeliveryFee> findByGovernorateIgnoreCase(String governorate);
}
