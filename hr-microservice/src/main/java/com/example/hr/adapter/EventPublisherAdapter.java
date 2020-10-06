package com.example.hr.adapter;

import org.springframework.stereotype.Service;

import com.example.hr.events.BusinessEvent;
import com.example.hr.infrastructure.EventPublisher;

@Service
public class EventPublisherAdapter implements EventPublisher {

	@Override
	public void publishEvent(BusinessEvent businessEvent) {
		// TODO Auto-generated method stub

	}

}
