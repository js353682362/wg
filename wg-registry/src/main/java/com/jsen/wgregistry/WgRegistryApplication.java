package com.jsen.wgregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WgRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WgRegistryApplication.class, args);
	}
}
