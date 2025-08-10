package com.example.hex.infrastructure.jpa;

import com.example.hex.domain.model.Order;
import com.example.hex.infrastructure.jpa.adapter.OrderRepositoryAdapter;
import com.example.hex.infrastructure.jpa.repo.OrderJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@EntityScan("com.example.hex.infrastructure.jpa.entity")
@EnableJpaRepositories("com.example.hex.infrastructure.jpa.repo")
@Import(OrderRepositoryAdapter.class)
@ContextConfiguration(classes = TestBootConfig.class)
class OrderRepositoryAdapterTest {

    @Autowired
    OrderRepositoryAdapter adapter;

    @Autowired
    OrderJpaRepository jpa;

    @Test
    void saveAndFind() {
        var created = adapter.save(new Order(null, "TestUser", new BigDecimal("5.00")));
        assertNotNull(created.getId());
        assertTrue(adapter.findById(created.getId()).isPresent());
        assertEquals(1, adapter.findAll().size());
        adapter.deleteById(created.getId());
        assertTrue(adapter.findAll().isEmpty());
    }
}
