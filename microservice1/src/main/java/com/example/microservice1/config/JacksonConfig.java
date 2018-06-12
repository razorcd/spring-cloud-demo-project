package com.example.microservice1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }
}
