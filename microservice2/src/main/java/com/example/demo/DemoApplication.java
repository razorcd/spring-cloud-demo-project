package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    private static final Logger LOGGER = Logger.getLogger(DemoApplication.class.getName());

    @RequestMapping(value = "/ids/generate", method = RequestMethod.GET)
	public String getTest() {
		Integer id = new Random().nextInt();
		LOGGER.info("Generated id: "+id);
        return id.toString();
	}
}
