package com.example.hex.application;

import com.example.hex.domain.model.Order;
import com.example.hex.domain.port.OrderRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRepo implements OrderRepository {
    private Map<Long, Order> store = new HashMap<>();
    private long seq = 0;
    public Order save(Order o){ if(o.getId()==null) o.setId(++seq); store.put(o.getId(), o); return o; }
    public Optional<Order> findById(Long id){ return Optional.ofNullable(store.get(id)); }
    public List<Order> findAll(){ return new ArrayList<>(store.values()); }
    public void deleteById(Long id){ store.remove(id); }
}

public class OrderServiceTest {

    @Test
    void createAndRead() {
        OrderService svc = new OrderService(new InMemoryRepo());
        Order created = svc.createOrder(new Order(null, "Alice", new BigDecimal("10.00")));
        assertNotNull(created.getId());
        assertTrue(svc.getOrder(created.getId()).isPresent());
    }
}
