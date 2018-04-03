! This project is still WIP

### Spring Cloud Demo Project

This repo serves as a demo project for various Spring Cloud infrastucture tools. The 3 microservices serve as dummy applications for the demo and they are comunicationg via http.
The system is receiving data through a public server, puts and id on the data and stores it. Each microservice has different concern that is described below.

#### Architecture
```
Microservice1 (Web Edge Server) -----> Microservice2 (generates random id)
    |
    ---------------------------------> Microservice3 (stores data)
```
#### Microservice1 (Web Edge Server) endpoints:

- public `curl -X GET http://localhost:8001/public/getall` - retuns all data
- public `curl -X POST http://localhost:8001/public/add -H 'content-type: text/plain' -d "some data"` - stores the data with a random id

#### Install

- `sdk install springboot`
- `spring install org.springframework.cloud:spring-cloud-cli:1.2.1.RELEASE`

#### Run

- start Spring Cloud `spring cloud eureka configserver zipkin`. By default it will load the `config/configserver.yml` as properties.
    - open Spring Eureka: http://localhost:8761/
    - open configserver `http://10.8.0.206:8888/microservice1/default` or `http://10.8.0.206:8888/microservice2/default` or `http://10.8.0.206:8888/microservice3/default`
    - open Zipkin `http://localhost:9411`
- oen Swagger UI: `http://localhost:8001/swagger-ui.html`



#### Dependecies used

- Eureka: service discovery
- ConfigServer: centralised configs
- Ribbon: load balancing for multiple service instances
- Feign: for client mapping
- Hystrix: circuit breaker fallback (also integrates with Feign for unreacheable services)
- Slouth and Zipkin: request logging with trace and monitoring




