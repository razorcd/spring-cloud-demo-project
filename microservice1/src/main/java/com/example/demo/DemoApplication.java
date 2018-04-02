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

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Value("${microservice2-endpoint}")
    String microservice2Endpoint;

	@Autowired
    RestTemplate restTemplate;


    @RequestMapping("/principal")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }

    @RequestMapping("/private")
	public String getTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(microservice2Endpoint+"/privatedata", String.class);

        return "Microservice1 - private response: " + responseEntity.getBody();
	}

    @RequestMapping("/public")
    public String getTestPublic() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(microservice2Endpoint+"/publicdata", String.class);

        return "Microservice1 - public response: " + responseEntity.getBody();
    }
}
