package com.example.hex.infrastructure.jpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private BigDecimal totalAmount;
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getCustomerName(){ return customerName; }
    public void setCustomerName(String customerName){ this.customerName = customerName; }
    public BigDecimal getTotalAmount(){ return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount){ this.totalAmount = totalAmount; }
}
