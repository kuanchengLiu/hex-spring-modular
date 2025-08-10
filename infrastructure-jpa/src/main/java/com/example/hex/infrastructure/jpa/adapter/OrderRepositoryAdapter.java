package com.example.hex.infrastructure.jpa.adapter;

import com.example.hex.domain.model.Order;
import com.example.hex.domain.port.OrderRepository;
import com.example.hex.infrastructure.jpa.entity.OrderEntity;
import com.example.hex.infrastructure.jpa.mapper.OrderMapper;
import com.example.hex.infrastructure.jpa.repo.OrderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository jpa;
    public OrderRepositoryAdapter(OrderJpaRepository jpa){ this.jpa = jpa; }

    @Override
    public Order save(Order order){
        OrderEntity saved = jpa.save(OrderMapper.toEntity(order));
        return OrderMapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id){
        return jpa.findById(id).map(OrderMapper::toDomain);
    }

    @Override
    public List<Order> findAll(){
        return jpa.findAll().stream().map(OrderMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id){
        jpa.deleteById(id);
    }
}
