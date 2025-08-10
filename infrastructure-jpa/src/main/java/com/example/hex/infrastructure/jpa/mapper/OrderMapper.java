package com.example.hex.infrastructure.jpa.mapper;

import com.example.hex.domain.model.Order;
import com.example.hex.infrastructure.jpa.entity.OrderEntity;

public class OrderMapper {
    public static Order toDomain(OrderEntity e){
        if(e==null) return null;
        return new Order(e.getId(), e.getCustomerName(), e.getTotalAmount());
    }
    public static OrderEntity toEntity(Order d){
        if(d==null) return null;
        OrderEntity e = new OrderEntity();
        e.setId(d.getId());
        e.setCustomerName(d.getCustomerName());
        e.setTotalAmount(d.getTotalAmount());
        return e;
    }
}
