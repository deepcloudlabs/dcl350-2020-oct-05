package com.example.lottery.service;

import java.util.List;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface LotteryService {

	List<List<Integer>> draw(int column);

}
