package com.example.lottery.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AsyncLotteryClientService {
	private static final String URL_LOTTERY = "http://localhost:8300/lottery/api/v1/numbers?n=10";
	@Autowired
	private WebClient webClient;
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryRestApi() {
		webClient.get()
		 		.uri(URL_LOTTERY)
		 		.header("Accept", "application/json")
				.retrieve()
				.bodyToMono(String.class)
				.subscribe( System.out::println, System.err::println);
	}

}
