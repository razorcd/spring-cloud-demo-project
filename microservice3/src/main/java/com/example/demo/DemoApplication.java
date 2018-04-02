package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private static final Logger LOGGER = Logger.getLogger(DemoApplication.class.getName());

	@Autowired
	StoreService storeService;

    @RequestMapping(value = "/data/{id}", method = RequestMethod.POST)
	//TODO: add different threads. Then start app with multiple threads.<
	public void add(@PathVariable String id, @RequestBody String data) {
    	LOGGER.info("Adding data("+data+") with id("+id+") to store.");
		storeService.add(id, data);
	}

	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public List<String> getAll() {
    	LOGGER.info("Getting all data.");
		return storeService.getAll();
	}

}
