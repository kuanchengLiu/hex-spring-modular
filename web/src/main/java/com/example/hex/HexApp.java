package com.example.hex;

import com.example.hex.application.OrderService;
import com.example.hex.domain.model.Order;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication(scanBasePackages = "com.example.hex")
public class HexApp {
    public static void main(String[] args) {
        SpringApplication.run(HexApp.class, args);
    }

    @Bean
    CommandLineRunner seed(OrderService service){
        return args -> {
            service.createOrder(new Order(null, "Alice", new BigDecimal("99.90")));
            service.createOrder(new Order(null, "Bob", new BigDecimal("42.00")));
        };
    }
}
