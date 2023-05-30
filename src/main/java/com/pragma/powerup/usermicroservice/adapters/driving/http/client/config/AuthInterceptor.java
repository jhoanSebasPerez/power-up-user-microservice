package com.pragma.powerup.usermicroservice.adapters.driving.http.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.stream.Collectors;

public class AuthInterceptor implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate template) {
        String token = template.headers().get("Authorization").stream()
                .filter(value -> value.startsWith("Bearer")).collect(Collectors.joining());
        template.header("Authorization", token);
    }
}
