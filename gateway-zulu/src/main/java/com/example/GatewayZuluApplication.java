package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayZuluApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayZuluApplication.class, args);
	}

}
