package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	StoreService storeService;

    @RequestMapping(value = "/data/{id}", method = RequestMethod.POST)
	//TODO: add different threads. Then start app with multiple threads.<
	public void add(@PathVariable String id, @RequestBody String data) {
        storeService.add(id, data);
	}

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public List<String> getAll() {
		return storeService.getAll();
	}

}
