package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.event.TradeEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class TradeWebsocketService {

	@Autowired
	private SimpMessagingTemplate template;

	@EventListener
	public void handleEvent(TradeEvent trade) {
		System.err.println("TradeWebsocketService has received one trade event: " + trade);
		template.convertAndSend("/topic/changes", trade);
	}
}
