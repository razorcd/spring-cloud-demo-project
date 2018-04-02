### Spring Cloud Demo Project

This repo serves as a demo project for various Spring Cloud infrastucture tools. The 3 microservices serve as dummy applications for the demo and they are comunicationg via http.
The system is receiving data through a public server, puts and id on the data and stores it. Each microservice has different concern that is described below.

#### Architecture

Microservice1 (Web Edge Server) -----> Microservice2 (generates random id)
    |
    ---------------------------------> Microservice3 (stores data)

#### Microservice1 (Web Edge Server) endpoints:

- public `GET localhost:8001/public/getall` - retuns all data
- public `POST localhost:8003/add  -d "some data"` - stores the data with a random id


