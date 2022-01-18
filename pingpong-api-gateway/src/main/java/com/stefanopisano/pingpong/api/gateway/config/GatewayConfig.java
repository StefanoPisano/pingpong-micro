package com.stefanopisano.pingpong.api.gateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator pingRoute(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/ping/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("CircuitBreaker").setFallbackUri("/pingServiceFallback")))
                        .uri("lb://PINGSERVICE"))
                .build();
    }

    @Bean
    public RouteLocator pongRoute(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/pong/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("CircuitBreaker").setFallbackUri("/pongServiceFallback")))
                        .uri("lb://PONGSERVICE"))
                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(4)).build()).build());
    }
}