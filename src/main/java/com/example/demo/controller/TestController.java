package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {
    @Value("${GOOGLE_CLIENT_ID:NOT_FOUND}")
    String googleId;

    @GetMapping("/check-env")
    public String check() {
        return googleId;
    }
}

