package com.connectify.backend.config.cors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfig {
    private final CorsProperties corsProperties;

    public CorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins(corsProperties.allowedOrigins().toArray(new String[0]))
                    .allowedMethods(corsProperties.allowedMethods().toArray(new String[0]))
                    .allowedHeaders(corsProperties.allowedHeaders().toArray(new String[0]))
                    .allowCredentials(corsProperties.allowCredentials());
            }
        };
    }
}
