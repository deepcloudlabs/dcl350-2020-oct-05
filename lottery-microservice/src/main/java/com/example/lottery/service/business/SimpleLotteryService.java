package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;

@Service
@RefreshScope
public class SimpleLotteryService implements LotteryService {

	@Value("${lottery.max}")
	private int lotteryMax;
	
	@Value("${lottery.size}")
	private int lotterySize;
	
	@Override
	public List<Integer> draw() {
		return ThreadLocalRandom.current().ints(1, lotteryMax)
				   .distinct()
				   .limit(lotterySize)
				   .sorted()
				   .boxed()
				   .collect(Collectors.toList());
	}

	@Override
	public List<List<Integer>> draw(int n) {
		return IntStream.range(0, n)
				  .mapToObj(i -> this.draw())
				  .collect(Collectors.toList());
	}

}
