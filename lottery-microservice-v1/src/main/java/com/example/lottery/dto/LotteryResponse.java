package com.example.lottery.dto;

import java.util.List;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class LotteryResponse {
	private List<List<Integer>> numbers;

	public LotteryResponse() {
	}

	public LotteryResponse(List<List<Integer>> numbers) {
		this.numbers = numbers;
	}

	public List<List<Integer>> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<List<Integer>> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "LotteryResponse [numbers=" + numbers + "]";
	}

}
