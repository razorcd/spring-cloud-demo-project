package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

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

    @RequestMapping("/private")
	public String getTest() {
        Integer id = new Random().nextInt();
        restTemplate.postForEntity(microservice3Endpoint+"/add", "private id " + id.toString(), null);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(microservice2Endpoint+"/privatedata/"+id, String.class);

        return "Microservice1 - private response: " + responseEntity.getBody();
	}

    @RequestMapping("/public")
    public String getTestPublic() {
        Integer id = new Random().nextInt();
        restTemplate.postForEntity(microservice3Endpoint+"/add", "public id " + id.toString(), null);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(microservice2Endpoint+"/publicdata/"+id, String.class);

        return "Microservice1 - public response: " + responseEntity.getBody();
    }

    @RequestMapping("/public/getall")
    public String getAll() {
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(microservice3Endpoint+"/getall", String[].class);

        return "Microservice1 - getall response: " + Arrays.toString(responseEntity.getBody());
    }
}
