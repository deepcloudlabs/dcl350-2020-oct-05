package com.example.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LotteryClientSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryClientSpringBootApplication.class, args);
	}

}
