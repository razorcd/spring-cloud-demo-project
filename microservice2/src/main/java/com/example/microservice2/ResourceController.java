package com.example.microservice2;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceController implements ResourceApi {

    private static final List<ResourceDto> resourceDtos = new ArrayList<>();

    @Override
    public List<ResourceDto> getResources() {
        return resourceDtos;
    }

    @Override
    public void createResource(@RequestBody  ResourceDto resourceDto) {
        resourceDtos.add(resourceDto);
    }

    @Override
    public void deleteResource(long id) {
        for (ResourceDto r : resourceDtos) {
            if (r.getId()==id) {
                resourceDtos.remove(r);
                return;
            }
        }
    }
}
