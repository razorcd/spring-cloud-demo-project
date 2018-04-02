package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	StoreService storeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)

	//TODO: add different threads. Then start app with multiple threads.<
	public void add(@RequestBody String id) {
        storeService.add(id);
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<String> getAll() {
		return storeService.getAll();
	}

}
