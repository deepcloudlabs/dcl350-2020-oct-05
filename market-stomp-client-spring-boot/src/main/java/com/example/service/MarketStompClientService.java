package com.example.service;

import java.lang.reflect.Type;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.example.event.TradeEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class MarketStompClientService implements StompSessionHandler {
	@Autowired
	private WebSocketStompClient stompClient;
	@Value("${stomp.url}")
	private String stompUrl;

	@PostConstruct
	public void connect() {
		stompClient.connect(stompUrl, this);
	}

	@Override
	public Type getPayloadType(StompHeaders stompHeaders) {
		return TradeEvent.class;
	}

	@Override
	public void handleFrame(StompHeaders header, Object payload) {
		TradeEvent trade = (TradeEvent) payload;
		System.err.println("Message received: " + trade);
	}

	@Override
	public void afterConnected(StompSession session, StompHeaders headers) {
		System.err.println("Connected!");
		session.subscribe("/topic/changes", this);
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] arg3,
			Throwable error) {
		System.err.println(error);
	}

	@Override
	public void handleTransportError(StompSession session, Throwable error) {
		System.err.println(error);
	}
}
