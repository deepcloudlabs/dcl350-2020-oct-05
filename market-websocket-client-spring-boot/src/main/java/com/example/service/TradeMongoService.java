package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.event.TradeEvent;
import com.example.repository.TradeEventRepository;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class TradeMongoService {
	@Autowired
	private TradeEventRepository repository;

	@EventListener
	public void handleEvent(TradeEvent trade) {
		repository.save(trade);
		System.err.println("TradeMongoService has received one trade event: " + trade);
	}
}
