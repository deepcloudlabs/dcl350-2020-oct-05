package com.example.crm.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.events.CustomerBaseEvent;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface BaseEventRepository extends MongoRepository<CustomerBaseEvent, String> {
	List<CustomerBaseEvent> findAll(PageRequest page);

	List<CustomerBaseEvent> findAllByIdentity(String identity);

}
