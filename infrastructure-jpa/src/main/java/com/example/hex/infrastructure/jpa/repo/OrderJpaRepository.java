package com.example.hex.infrastructure.jpa.repo;

import com.example.hex.infrastructure.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> { }
