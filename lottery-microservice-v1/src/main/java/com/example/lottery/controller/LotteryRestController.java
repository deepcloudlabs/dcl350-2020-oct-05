package com.example.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.dto.LotteryResponse;
import com.example.lottery.service.LotteryService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController
@RequestScope
@RequestMapping("numbers")
@CrossOrigin
public class LotteryRestController {
	@Autowired
	private LotteryService lotteryService;
	@Value("${server.address}")
	private String host;
	@Value("${server.port}")
	private int port;

	// GET http://localhost:8001/lottery/api/v1/numbers?column=10
	@GetMapping(params = { "column" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public LotteryResponse getNumbers(@RequestParam int column) {
		System.err.println(String.format("Lottery Service @ %s:%d is serving now...", host, port));
		return new LotteryResponse(lotteryService.draw(column));
	}
}
