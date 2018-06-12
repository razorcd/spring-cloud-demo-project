package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/resource")
public interface ResourceApi {

    @RequestMapping(method = RequestMethod.GET)
    List<ResourceDto> getResources();

    @RequestMapping(method = RequestMethod.POST)
    void createResource(@RequestBody ResourceDto resourceDto);

    @RequestMapping(method = RequestMethod.DELETE)
    void deleteResource(@PathVariable long id);
}
