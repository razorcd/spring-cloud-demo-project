package com.example.microservice1.client;

import com.example.microservice2.ResourceApi;
import com.example.microservice2.ResourceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceClientConfigTest {

    @Autowired
    private ResourceApi resourceApi;

    @Test
    public void getResourceApi() {
        ResourceDto resourceDto = new ResourceDto(1, "data1");

        resourceApi.createResource(resourceDto);

        List<ResourceDto> requestedResourceDtos = resourceApi.getResources();

        assertEquals(resourceDto, requestedResourceDtos.get(0));
        assertEquals(resourceDto.getData(), requestedResourceDtos.get(0).getData());
    }
}