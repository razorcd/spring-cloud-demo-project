package com.example.microservice1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resourceforwarder")
public class ResourceForwarderController {

    @Autowired
    com.example.microservice2.ResourceApi resourceApi;

    @RequestMapping(method = RequestMethod.GET)
    List<com.example.microservice2.ResourceDto> getResources() {
        return resourceApi.getResources();
    }
}
