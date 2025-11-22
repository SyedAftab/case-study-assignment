package com.justlife.gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Aftab
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder()
                .group("customers")
                .pathsToMatch("/customers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi cleanerApi() {
        return GroupedOpenApi.builder()
                .group("cleaners")
                .pathsToMatch("/cleaners/**")
                .build();
    }

    @Bean
    public GroupedOpenApi vehicleApi() {
        return GroupedOpenApi.builder()
                .group("vehicles")
                .pathsToMatch("/vehicles/**")
                .build();
    }

    @Bean
    public GroupedOpenApi bookingApi() {
        return GroupedOpenApi.builder()
                .group("bookings")
                .pathsToMatch("/bookings/**")
                .build();
    }

}
