package com.webservices.company.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/hellopath")
@RestController
public class HelloSpringBootController {


    @GetMapping
    public String hello(){
        return "hello from spring boot ";
    }



}
