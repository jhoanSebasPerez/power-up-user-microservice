package com.pragma.powerup.usermicroservice.adapters.driving.http.client.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

    @Bean
    public Contract.Default feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
