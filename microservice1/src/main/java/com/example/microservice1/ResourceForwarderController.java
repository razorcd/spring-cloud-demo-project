package com.example.microservice1;

import com.example.microservice2.ResourceApi;
import com.example.microservice2.ResourceDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.*;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resourceforwarder")
public class ResourceForwarderController {

    ResourceApi resourceApi;

    @Autowired
    public ResourceForwarderController(Client feignClinet) {

        this.resourceApi= Feign.builder()
                .client(feignClinet)
//                .client(new OkHttpClient())
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(ResourceApi.class))
                .logLevel(Logger.Level.FULL)
                .target(ResourceApi.class, "http://localhost:8081");

//        		<dependency>
//			<groupId>io.github.openfeign</groupId>
//			<artifactId>feign-okhttp</artifactId>
//			<version>9.7.0</version>
//		</dependency>
//
//		<dependency>
//			<groupId>io.github.openfeign</groupId>
//			<artifactId>feign-gson</artifactId>
//			<version>9.7.0</version>
//		</dependency>
//
//		<dependency>
//			<groupId>io.github.openfeign</groupId>
//			<artifactId>feign-slf4j</artifactId>
//			<version>9.7.0</version>
//		</dependency>
    }

    @RequestMapping(method = RequestMethod.GET)
    List<ResourceDto> getResources() {
//        return Arrays.asList(new ResourceDto(1,"data1"));
        return resourceApi.getResources();
    }
}
