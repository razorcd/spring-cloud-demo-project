package com.example.microservice1;

import com.example.microservice2.ResourceApi;
import com.example.microservice2.ResourceDto;
import feign.Feign;
import feign.Logger;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resourceforwarder")
public class ResourceForwarderController {

    ResourceApi resourceApi;

    public ResourceForwarderController() {

        this.resourceApi= Feign.builder()
                .client(client)
                .contract(new SpringMvcContract())
//                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
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
