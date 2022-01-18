package com.stefanopisano.pingpong.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PingpongApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingpongApiGatewayApplication.class, args);
	}
}
