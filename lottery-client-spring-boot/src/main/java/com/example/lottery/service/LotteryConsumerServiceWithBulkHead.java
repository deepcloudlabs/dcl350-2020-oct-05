package com.example.lottery.service;

import java.net.SocketException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@Service
public class LotteryConsumerServiceWithBulkHead {
	
	@Bulkhead(name="lotterySrvBulkHead", fallbackMethod = "getNumbersFallback",
			   type=Type.THREADPOOL)
	public String getNumbers() {
		RestTemplate rt = new RestTemplate();
		return rt.getForEntity("http://localhost:8300/lottery/api/v1/numbers?n=10", String.class).getBody();
	}

	public String getNumbersFallback(Exception e) {
		if (e instanceof SocketException)
			return "[[1,2,3,4,5,6]]";
		return "[[4,8,15,16,23,42]]";
	}
}
