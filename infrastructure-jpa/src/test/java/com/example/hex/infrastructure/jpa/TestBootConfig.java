package com.example.hex.infrastructure.jpa;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/** 測試專用的最小啟動組態（只掃描 infra-jpa 套件） */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.example.hex.infrastructure.jpa")
class TestBootConfig {
}
