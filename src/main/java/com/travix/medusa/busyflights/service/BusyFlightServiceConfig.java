package com.travix.medusa.busyflights.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BusyFlightServiceConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
