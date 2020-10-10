package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.event.TradeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class MarketClientService implements WebSocketHandler {
	@Autowired
	private WebSocketClient websocketClient;
	@Value("${market.url}")
	private String marketUrl;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@PostConstruct
	public void init() {
		websocketClient.doHandshake(this, marketUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the market");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String payload = message.getPayload().toString();
		TradeEvent trade = mapper.readValue(payload, TradeEvent.class);
		eventPublisher.publishEvent(trade);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err.println("An error has occurred: " + exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Connection is closed!");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
