package com.example.microservice1;

import com.example.microservice1.client.microservice2.DemoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.logging.Logger;

@RestController
@Api(value = "Demo", description = "Spring Cloud demo project - Edge server")
public class DemoController {

	private static final Logger LOGGER = Logger.getLogger(DemoController.class.getName());

	@Value("${microservice2-endpoint}")
    String microservice2Endpoint;

    @Value("${microservice3-endpoint}")
    String microservice3Endpoint;

	@Autowired
    RestTemplate restTemplate;

	@Autowired
    DemoService microservice2DemoService;


    @RequestMapping(value = "/principal", method = RequestMethod.GET)
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


    @RequestMapping(value = "/public/add", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Add data", notes = "Stores data with a random id.")
    public void addData(@RequestBody String data) {
        LOGGER.info("Requesting id from microservice2.");
//        ResponseEntity<String> idResponse = restTemplate.getForEntity(microservice2Endpoint+"/ids/generate", String.class);
        String idResponse = microservice2DemoService.generateId();

        LOGGER.info("Sending data ("+data+") with id (" + idResponse + ") to microservice3.");
        restTemplate.postForEntity(microservice3Endpoint+"/data/" + idResponse, data, null);
    }


    @RequestMapping(value = "/public/getall", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Get all data", notes = "Returns the list of all data and ids.")
    @HystrixCommand(fallbackMethod = "getAllFallback")
    public String getAll() {
//        if (0==0) throw new RuntimeException();  // will be caught by fallback method
        LOGGER.info("Requesting all data from microservice3.");
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(microservice3Endpoint+"/data", String[].class);

        return "Microservice1 - getall response: " + Arrays.toString(responseEntity.getBody());
    }


    // method called by Hystrix if the main method fails
    private String getAllFallback() {
        return "/public/getAll FALLBACK because microservice3 is down !!!!!!!!!!!!!!!!!";
    }
}
