package com.example.crm.service.business;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.events.CustomerBaseEvent;
import com.example.crm.events.CustomerCreatedEvent;
import com.example.crm.events.CustomerEmailChangedEvent;
import com.example.crm.events.CustomerPhotoChangedEvent;
import com.example.crm.repository.BaseEventRepository;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerCommandService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class StandardCustomerCommandService implements CustomerCommandService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BaseEventRepository eventRepository;

	@Autowired
	private KafkaTemplate<String, CustomerBaseEvent> kafkaTemplate;

	@Override
	public Customer createCustomer(Customer customer) {
		String identity = customer.getIdentity();
		var customerEvents = eventRepository.findAllByIdentity(identity);
		if (!customerEvents.isEmpty()) {
			throw new IllegalArgumentException("Customer already exists");
		}
		customerRepository.save(customer);
		CustomerCreatedEvent customerEvent = new CustomerCreatedEvent();
		customerEvent.setIdentity(identity);
		customerEvent.setEventId(UUID.randomUUID().toString());
		customerEvent.setCustomer(customer);
		eventRepository.save(customerEvent);
		kafkaTemplate.send("customer-events", customerEvent);
		return customer;
	}

	@Override
	public Customer updateCustomerByIdentity(String identity, Map<String, Object> request) {
		var managedCustomer = customerRepository.findById(identity);
		if (managedCustomer.isPresent()) {
			var customer = managedCustomer.get();
			request.forEach((field, value) -> {
				Field declaredField;
				try {
					declaredField = Customer.class.getDeclaredField(field);
					if (field.equals("photo")) {
						CustomerPhotoChangedEvent event = new CustomerPhotoChangedEvent();
						event.setIdentity(identity);
						event.setEventId(UUID.randomUUID().toString());
						event.setPhoto(value.toString());
						eventRepository.save(event);
					} else if (field.equals("email")) {
						CustomerEmailChangedEvent event = new CustomerEmailChangedEvent();
						event.setIdentity(identity);
						event.setEventId(UUID.randomUUID().toString());
						event.setEmail(value.toString());
						eventRepository.save(event);
					}
					declaredField.setAccessible(true);
					declaredField.set(customer, value);
					declaredField.setAccessible(false);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			});
			return customerRepository.save(customer);
		}
		throw new IllegalArgumentException("Customer does not exist");
	}

	@Override
	public Customer deleteCustomerByIdentity(String identity) {
		var customer = customerRepository.findById(identity);
		if (customer.isPresent()) {
			Customer removedCustomer = customer.get();
			customerRepository.delete(removedCustomer);
			return removedCustomer;
		}
		throw new IllegalArgumentException("Customer does not exist");
	}

}
