package com.example.crm.listener;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import com.example.crm.events.CustomerBaseEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Component
public class BeforeSaveListener extends AbstractMongoEventListener<CustomerBaseEvent> {
	@Override
	public void onBeforeSave(BeforeSaveEvent<CustomerBaseEvent> event) {

		Date timestamp = new Date();

		// Add a timestamp to the created date if it does not yet exist
		if (event.getSource().getCreatedAt() == null)
			event.getSource().setCreatedAt(timestamp);

		// Update the timestamp to the current time
		event.getSource().setLastModified(timestamp);

		super.onBeforeSave(event);
	}
}
