package com.example.hex.domain.port;

import com.example.hex.domain.model.Order;
import java.util.List;
import java.util.Optional;

/** Port: persistence operations required by domain */
public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    void deleteById(Long id);
}
