package com.example.microservice1.client;

import com.example.microservice2.ResourceApi;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourceClientConfig {

//    @Value("app.microservice1.url")
    private String microservice1Url = "http://localhost:8081";

    @Autowired
    private Client feignClient;

    @Bean
    public ResourceApi getResourceApi() {
        return Feign.builder()
                .client(feignClient)
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(ResourceApi.class))
                .logLevel(Logger.Level.FULL)
                .target(ResourceApi.class, microservice1Url);
    }
}
