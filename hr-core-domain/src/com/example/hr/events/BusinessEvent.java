package com.example.hr.events;

import java.util.UUID;

public class BusinessEvent {
	protected final String topic;
	protected final String id;

	public BusinessEvent(String topic) {
		this.topic = topic;
		id = UUID.randomUUID().toString();
	}

	public String getTopic() {
		return topic;
	}

	public String getId() {
		return id;
	}

}
