package com.example.lottery.controller;

import java.util.List;

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
	public List<List<Integer>> getLotteryNumbers(){
		return List.of(List.of(4,8,15,16,23,42));
	}
}
