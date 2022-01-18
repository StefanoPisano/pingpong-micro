package com.stefanopisano.pingpong.api.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackMethodController {

    @GetMapping("/pingServiceFallback")
    public String pingServiceFallbackMethod() {
        return "Ping Service is down.";
    }

    @GetMapping("/pongServiceFallback")
    public String pongServiceFallbackMethod() {
        return "Pong Service is down.";
    }
}
