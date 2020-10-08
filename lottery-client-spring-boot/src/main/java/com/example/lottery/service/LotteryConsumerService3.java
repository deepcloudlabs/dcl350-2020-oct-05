package com.example.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryConsumerService3 {
	private static final String URL = "//lottery/lottery/api/v1/numbers?n=10";

	@Autowired
	private RestTemplate restTemplate;
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var numbers = restTemplate.getForEntity(URL, String.class).getBody();
		System.out.println(numbers);
	}
}
