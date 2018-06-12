package com.example.microservice1;

import com.example.microservice2.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resourceforwarder")
public class ResourceForwarderController {

//    @Autowired
//    com.example.microservice2.ResourceApi resourceApi;

    @RequestMapping(method = RequestMethod.GET)
    List<ResourceDto> getResources() {
        return Arrays.asList(new ResourceDto(1,"data1"));
//        return resourceApi.getResources();
    }
}
