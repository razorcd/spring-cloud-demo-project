package com.example.microservice1.client.microservice2;

import org.springframework.stereotype.Component;

@Component
public class DemoServiceFallbackImpl implements DemoService {

    @Override
    public String generateId() {
        return "ungenerated_id";
    }
}
