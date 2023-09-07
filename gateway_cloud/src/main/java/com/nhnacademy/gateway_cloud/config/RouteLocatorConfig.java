package com.nhnacademy.gateway_cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/task/**").and()
                        .uri("http://localhost:6060")) // task
                .route(p -> p.path("/account/**").and()
                        .uri("http://localhost:7070")) // account
                .build();
    }
}
