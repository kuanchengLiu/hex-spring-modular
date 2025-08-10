package com.example.hex.application;

import com.example.hex.domain.model.Order;
import com.example.hex.domain.port.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repo;
    public OrderService(OrderRepository repo){ this.repo = repo; }

    @Transactional
    public Order createOrder(Order order){ return repo.save(order); }

    @Transactional(readOnly = true)
    public Optional<Order> getOrder(Long id){ return repo.findById(id); }

    @Transactional(readOnly = true)
    public List<Order> listOrders(){ return repo.findAll(); }

    @Transactional
    public void deleteOrder(Long id){ repo.deleteById(id); }
}
