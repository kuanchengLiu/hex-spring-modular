package com.example.hex.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void applyDiscount_shouldReduceTotalAmount() {
        Order o = new Order(1L, "Alice", new BigDecimal("100.00"));
        o.applyDiscount(new BigDecimal("10"));
        assertEquals(new BigDecimal("90.0000"), o.getTotalAmount().setScale(4));
    }

    @Test
    void applyDiscount_outOfRange_shouldThrow() {
        Order o = new Order(1L, "Alice", new BigDecimal("100.00"));
        assertThrows(IllegalArgumentException.class, () -> o.applyDiscount(new BigDecimal("120")));
    }
}
