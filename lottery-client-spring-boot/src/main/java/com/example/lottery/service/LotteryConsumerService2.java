package com.example.lottery.service;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;

//@Service
public class LotteryConsumerService2 {
	private static final String URL = "http://%s:%d/lottery/api/v1/numbers?n=10";

	@Autowired
	private DiscoveryClient discoveryClient;
	private BaseLoadBalancer loadBalancer;
	
	@PostConstruct
	public void init() {
		 var servers = discoveryClient.getInstances("lottery")
				 .stream()
				 .map( instance -> new Server(instance.getHost(), instance.getPort()) )
				 .collect(Collectors.toList());
		 IRule roundRobinRule = new RoundRobinRule();
		 loadBalancer = LoadBalancerBuilder.newBuilder()
				                           .withRule(roundRobinRule)
				                           .buildFixedServerListLoadBalancer(servers);
		 servers.forEach(System.err::println);
	}
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var rt = new RestTemplate();
		var server = loadBalancer.chooseServer();
		String lotteryUrl = String.format(URL,server.getHost(), server.getPort());
		System.err.println("Sending request to "+lotteryUrl);
		var numbers = rt.getForEntity(lotteryUrl, String.class).getBody();
		System.out.println(numbers);
	}
}
