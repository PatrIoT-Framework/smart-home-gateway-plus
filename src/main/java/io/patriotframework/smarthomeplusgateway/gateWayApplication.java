package io.patriotframework.smarthomeplusgateway;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class gateWayApplication {


    @Autowired
    GateWay gateWay;


    /**
     * Main method, where the Spring Boot container is executed.
     *
     * @param args args given to Spring Boot application
     */
    public static void main(String[] args) throws RuntimeException {
            SpringApplication.run(gateWayApplication.class, args);
    }

}