package com.example.demo.config;

import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    @Bean // to be traced with Sleuth in Zipkin
    public Sampler getSampler() {
        return new AlwaysSampler();
    }
}
