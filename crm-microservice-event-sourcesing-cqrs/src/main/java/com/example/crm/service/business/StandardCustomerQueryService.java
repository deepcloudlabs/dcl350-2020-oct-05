package com.example.crm.service.business;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.crm.document.Customer;
import com.example.crm.events.CustomerBaseEvent;
import com.example.crm.events.CustomerCreatedEvent;
import com.example.crm.events.CustomerEmailChangedEvent;
import com.example.crm.events.CustomerPhotoChangedEvent;
import com.example.crm.events.CustomerRemovedEvent;
import com.example.crm.repository.BaseEventRepository;
import com.example.crm.service.CustomerQueryService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Service
public class StandardCustomerQueryService implements CustomerQueryService {

	@Autowired
	private BaseEventRepository eventRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Customer> findAllCustomers(int pagesize, int pageno) {
		var pagination = new Query();
		pagination.with(PageRequest.of(pageno, pagesize));
		var events = mongoTemplate.query(CustomerBaseEvent.class).distinct("identity").matching(pagination)
				.as(CustomerBaseEvent.class).all();
		return events.stream().map(event -> this.findCustomerByIdentity(event.getIdentity()))
				.collect(Collectors.toList());
	}

	@Override
	public Customer findCustomerByIdentity(String identity) {
		var events = eventRepository.findAllByIdentity(identity);
		Customer customer = null;
		for (var event : events) {
			if (event instanceof CustomerCreatedEvent) {
				customer = CustomerCreatedEvent.class.cast(event).getCustomer();
			} else if (event instanceof CustomerEmailChangedEvent) {
				var email = CustomerEmailChangedEvent.class.cast(event).getEmail();
				Objects.requireNonNull(customer);
				customer.setEmail(email);
			} else if (event instanceof CustomerPhotoChangedEvent) {
				var photo = CustomerPhotoChangedEvent.class.cast(event).getPhoto();
				Objects.requireNonNull(customer);
				customer.setPhoto(photo);
			} else if (event instanceof CustomerRemovedEvent) {
				Objects.requireNonNull(customer);
				customer = null;
			}
		}
		if (Objects.isNull(customer))
			throw new IllegalArgumentException("Cannot find customer");
		return customer;
	}

}
