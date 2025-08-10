package com.example.hex.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/** Domain Object (pure domain, no JPA) */
public class Order {
    private Long id;
    private String customerName;
    private BigDecimal totalAmount;

    public Order() { }
    public Order(Long id, String customerName, BigDecimal totalAmount) {
        this.id = id; this.customerName = customerName; this.totalAmount = totalAmount;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public void applyDiscount(BigDecimal percent) {
        if (percent.compareTo(BigDecimal.ZERO) < 0 || percent.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("percent out of range");
        }
        BigDecimal factor = BigDecimal.ONE.subtract(percent.movePointLeft(2));
        this.totalAmount = this.totalAmount.multiply(factor);
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order other = (Order) o;
        return Objects.equals(id, other.id) &&
               Objects.equals(customerName, other.customerName) &&
               Objects.equals(totalAmount, other.totalAmount);
    }
    @Override public int hashCode() { return Objects.hash(id, customerName, totalAmount); }
}
