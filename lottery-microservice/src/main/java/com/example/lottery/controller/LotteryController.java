package com.example.lottery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("numbers")
public class LotteryController {
	@Autowired
	private LotteryService lotteryService;

	@GetMapping(params= {"n"})
	public List<List<Integer>> getLotteryNumbers(@RequestParam int n){
		return lotteryService.draw(n);
	}
}
