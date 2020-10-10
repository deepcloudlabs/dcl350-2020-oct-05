package com.example.lottery.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("numbers")
public class LotteryController {
	
	// http://localhost:8300/lottery/api/v1/numbers
	@GetMapping
	public List<Integer> getLotteryNumbers(){
		return ThreadLocalRandom.current().ints(1, 50)
				 .distinct()
				 .limit(6)
				 .sorted()
				 .boxed()
				 .collect(Collectors.toList());
	}
}
