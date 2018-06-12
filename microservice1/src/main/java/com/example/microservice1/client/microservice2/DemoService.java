package com.example.microservice1.client.microservice2;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "microservice2", fallback = DemoServiceFallbackImpl.class)
public interface DemoService {

    @RequestMapping(value = "/ids/generate", method = RequestMethod.GET)
    String generateId();
}
