package com.example.hr.infrastructure;

import com.example.hr.events.BusinessEvent;

public interface EventPublisher {

	void publishEvent(BusinessEvent businessEvent);

}
