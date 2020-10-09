package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.CustomerReactiveCustomer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
public class CustomerReactiveController {
	@Autowired
	private CustomerReactiveCustomer customerService;
	
	@GetMapping(params = {"page", "size"})
	public Flux<CustomerDocument> findAllCustomers(
			@RequestParam int page,@RequestParam int size){
		return customerService.findAllCustomers(page,size);
	}
	
	@GetMapping("{identity}")
	public Mono<CustomerDocument> findCustomerByIdentity(@PathVariable String identity){
		return customerService.findByIdentity(identity);
	}
}
