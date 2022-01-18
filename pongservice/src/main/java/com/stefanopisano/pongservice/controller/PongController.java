package com.stefanopisano.pongservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pong")
public class PongController {

    @GetMapping
    public String getPong() {
        return "Pong";
    }
}
