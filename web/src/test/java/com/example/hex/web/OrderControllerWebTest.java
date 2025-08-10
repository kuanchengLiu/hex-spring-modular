package com.example.hex.web;

import com.example.hex.application.OrderService;
import com.example.hex.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerWebTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    OrderService service;

    @Test
    void list_shouldReturnOk() throws Exception {
        Mockito.when(service.listOrders()).thenReturn(List.of(new Order(1L, "A", new BigDecimal("1.00"))));
        mvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("A")));
    }

    @Test
    void get_should404_whenMissing() throws Exception {
        Mockito.when(service.getOrder(99L)).thenReturn(Optional.empty());
        mvc.perform(get("/api/orders/99")).andExpect(status().isNotFound());
    }

    @Test
    void create_shouldReturnCreated() throws Exception {
        Mockito.when(service.createOrder(Mockito.any()))
                .thenReturn(new Order(10L, "C", new BigDecimal("2.00")));

        mvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerName\":\"C\",\"totalAmount\":2.00}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/orders/10"));
    }
}
