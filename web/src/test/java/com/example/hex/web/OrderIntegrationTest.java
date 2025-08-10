package com.example.hex.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Test
    void endToEnd_create_and_get() {
        String base = "http://localhost:" + port + "/api/orders";
        ResponseEntity<Map> created = rest.postForEntity(base, Map.of("customerName", "Zoe", "totalAmount", 12.34), Map.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String location = created.getHeaders().getLocation().toString();

        ResponseEntity<String> list = rest.getForEntity(base, String.class);
        assertThat(list.getBody()).contains("Zoe");

        ResponseEntity<String> get = rest.getForEntity(location, String.class);
        assertThat(get.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
