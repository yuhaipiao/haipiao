package com.haipiao.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthzController {

    @GetMapping("/healthz")
    @ResponseStatus(HttpStatus.OK)
    public String getHealthz() {
        return "ok";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String getRoot() {
        return "ok";
    }
}
