package com.jsen.wgzuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
@EnableZuulProxy
public class WgZuulApplication {
    private static Logger log = LoggerFactory.getLogger(WgZuulApplication.class);

	public static void main(String[] args) {
		log.info("starting zuul.......");
		SpringApplication.run(WgZuulApplication.class, args);
		log.info("zuul started");
	}
}
