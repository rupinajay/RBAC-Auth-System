package com.securityassignment.authentication_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "Application is running successfully!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from RBAC Security Application!";
    }
}