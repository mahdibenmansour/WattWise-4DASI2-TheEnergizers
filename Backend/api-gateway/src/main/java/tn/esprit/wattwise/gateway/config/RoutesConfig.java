package tn.esprit.wattwise.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Public routes of WattWise TN. "lb://name" is resolved through Eureka,
 * so the name must match each service's spring.application.name.
 */
@Configuration
public class RoutesConfig {

    @Bean
    public RouteLocator wattwiseRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Business routes (planned API contracts)
                .route("ms1-household", r -> r.path("/api/v1/households/**")
                        .uri("lb://ms1-household-service"))
                .route("ms2-consumption", r -> r.path("/api/v1/consumption/**", "/api/v1/bills/**", "/api/v1/tariffs/**")
                        .uri("lb://ms2-consumption-service"))
                .route("ms3-solar", r -> r.path("/api/v1/solar/**")
                        .uri("lb://ms3-solar-service"))
                .route("ms4-challenges", r -> r.path("/api/v1/challenges/**", "/api/v1/rewards/**")
                        .uri("lb://ms4-challenge-service"))
                .route("ms5-assistant", r -> r.path("/api/v1/assistant/**")
                        .uri("lb://ms5-optimization-service"))
                .route("ms6-outages", r -> r.path("/api/v1/outages/**")
                        .uri("lb://ms6-outage-service"))
                .route("ms7-marketplace", r -> r.path("/api/v1/marketplace/**")
                        .uri("lb://ms7-marketplace-service"))

                // Session 4 check routes: /msN/... is forwarded to service N without the prefix,
                // e.g. GET /ms3/api/v1/hello -> ms3-solar-service GET /api/v1/hello
                .route("ms1-check", r -> r.path("/ms1/**").filters(f -> f.stripPrefix(1)).uri("lb://ms1-household-service"))
                .route("ms2-check", r -> r.path("/ms2/**").filters(f -> f.stripPrefix(1)).uri("lb://ms2-consumption-service"))
                .route("ms3-check", r -> r.path("/ms3/**").filters(f -> f.stripPrefix(1)).uri("lb://ms3-solar-service"))
                .route("ms4-check", r -> r.path("/ms4/**").filters(f -> f.stripPrefix(1)).uri("lb://ms4-challenge-service"))
                .route("ms5-check", r -> r.path("/ms5/**").filters(f -> f.stripPrefix(1)).uri("lb://ms5-optimization-service"))
                .route("ms6-check", r -> r.path("/ms6/**").filters(f -> f.stripPrefix(1)).uri("lb://ms6-outage-service"))
                .route("ms7-check", r -> r.path("/ms7/**").filters(f -> f.stripPrefix(1)).uri("lb://ms7-marketplace-service"))
                .build();
    }
}
