package com.example.lottery.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@Service
public class LotteryConsumerServiceWithRateLimiter {
	@RateLimiter(name="lotterySrvLimiter")
	public String getNumbers() {
		RestTemplate rt = new RestTemplate();
		return rt.getForEntity("http://localhost:8300/lottery/api/v1/numbers?n=10", String.class).getBody();
	}
}
