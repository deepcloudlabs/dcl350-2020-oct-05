package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryConsumerService1 {
	private static final String URL = "http://%s:%d/lottery/api/v1/numbers?n=10";

	@Autowired
	private DiscoveryClient discoveryClient;
	private List<ServiceInstance> servers;
	private AtomicInteger counter= new AtomicInteger(0);
	
	@PostConstruct
	public void init() {
		 servers = discoveryClient.getInstances("lottery");
		 servers.forEach(System.err::println);
	}
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var rt = new RestTemplate();
		var index = counter.getAndIncrement() % servers.size();
		var server = servers.get(index);
		String lotteryUrl = String.format(URL,server.getHost(), server.getPort());
		System.err.println("Sending request to "+lotteryUrl);
		var numbers = rt.getForEntity(lotteryUrl, String.class).getBody();
		System.out.println(numbers);
	}
}
