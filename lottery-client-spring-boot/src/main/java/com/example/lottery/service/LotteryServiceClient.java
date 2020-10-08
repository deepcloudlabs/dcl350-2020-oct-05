package com.example.lottery.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryServiceClient {
	private static final String URL = "http://localhost:8300/lottery/api/v1/numbers?n=10";

	@Scheduled(fixedRate = 3_000)
	public void callLotteryRestApi() {
		var rt = new RestTemplate();
		var numbers = rt.getForEntity(URL, String.class).getBody();
		System.out.println(numbers);
	}
}
