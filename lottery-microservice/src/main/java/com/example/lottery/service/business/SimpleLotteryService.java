package com.example.lottery.service.business;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;

@Service
public class SimpleLotteryService implements LotteryService {

	@Override
	public List<Integer> draw() {
		return ThreadLocalRandom.current().ints(1, 50)
				   .distinct()
				   .limit(6)
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
