package com.example.lottery.service;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudyRetryStrategy {

	@Retryable(value = { SocketTimeoutException.class,
			SocketException.class }, maxAttempts = 3, backoff = @Backoff(multiplier = 2, delay = 3_000))
	public String getNumbers() {
		RestTemplate rt = new RestTemplate();
		return rt.getForEntity("http://localhost:8300/lottery/api/v1/numbers?n=10", String.class).getBody();
	}

	@Recover
	public String getNumbersFallback(Exception e) {
		if (e instanceof SocketException)
		   return "[[1,2,3,4,5,6]]";
		return "[[4,8,15,16,23,42]]";
	}
}
