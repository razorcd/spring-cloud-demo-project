package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
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

	@Value("${microservice2-endpoint}")
    String microservice2Endpoint;

    @Value("${microservice3-endpoint}")
    String microservice3Endpoint;

	@Autowired
    RestTemplate restTemplate;


    @RequestMapping("/principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

//    @RequestMapping("/private")
//	public String getTest() {
//        Integer id = new Random().nextInt();
//        restTemplate.postForEntity(microservice3Endpoint+"/add", "private id " + id.toString(), null);
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(microservice2Endpoint+"/privatedata/"+id, String.class);
//
//        return "Microservice1 - private response: " + responseEntity.getBody();
//	}

    @RequestMapping(value = "/public/add", method = RequestMethod.POST)
    public void addData(@RequestBody String data) {
        LOGGER.info("Requesting id from microservice2.");
        ResponseEntity<String> idResponse = restTemplate.getForEntity(microservice2Endpoint+"/ids/generate", String.class);

        LOGGER.info("Sending data ("+data+") with id (" + idResponse.getBody() + ") to microservice3.");
        restTemplate.postForEntity(microservice3Endpoint+"/data/" + idResponse.getBody(), data, null);
    }

    @RequestMapping("/public/getall")
    public String getAll() {
        LOGGER.info("Requesting all data from microservice3.");
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(microservice3Endpoint+"/data", String[].class);

        return "Microservice1 - getall response: " + Arrays.toString(responseEntity.getBody());
    }
}
