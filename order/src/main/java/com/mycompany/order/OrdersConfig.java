package com.mycompany.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrdersConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
