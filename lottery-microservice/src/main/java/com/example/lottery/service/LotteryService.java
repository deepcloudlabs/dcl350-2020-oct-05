package com.example.lottery.service;

import java.util.List;

public interface LotteryService {

	List<Integer> draw();

	List<List<Integer>> draw(int n);

}
