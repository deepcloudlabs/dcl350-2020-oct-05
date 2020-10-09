package com.example.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerReactiveCustomer {
	@Autowired
	private CustomerDocumentReactiveRepository customerRepository;
	
	public Flux<CustomerDocument> findAllCustomers(int page, int size) {
		return customerRepository.findAll(PageRequest.of(page, size));
	}

	public Mono<CustomerDocument> findByIdentity(String identity) {
		return customerRepository.findById(identity);
	}

}
