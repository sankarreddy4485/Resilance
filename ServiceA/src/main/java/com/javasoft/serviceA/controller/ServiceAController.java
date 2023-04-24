package com.javasoft.serviceA.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/a")
public class ServiceAController {

    @Autowired
    public RestTemplate restTemplate;
public static final String BASE_URL="http://localhost:8082/";
public static final String SERVICE_A = "serviceA";
    int count =0;
@GetMapping
@CircuitBreaker(name=SERVICE_A , fallbackMethod = "serviceAFallback")
//@Retry(name = SERVICE_A ,fallbackMethod = "serviceAFallback")
    public String serviceA(){

System.out.println("retry method called "+count++ +"times at"+new Date());
        String url=BASE_URL+"/b";
        return restTemplate.getForObject(url,String.class);

    }
    public String serviceAFallback(Exception e){
    return "this is a fallback method for service A";
    }
}
