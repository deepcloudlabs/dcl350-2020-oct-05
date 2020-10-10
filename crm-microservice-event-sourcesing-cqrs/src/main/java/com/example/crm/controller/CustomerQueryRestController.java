package com.example.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.document.Customer;
import com.example.crm.service.CustomerQueryService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@RestController
@RequestScope
@RequestMapping("customers")
@CrossOrigin
public class CustomerQueryRestController {
	@Autowired
	private CustomerQueryService customerService;

	@GetMapping(params = { "pagesize", "pageno" })
	public List<Customer> getAllCustomers(@RequestParam int pagesize, @RequestParam int pageno) {
		return customerService.findAllCustomers(pagesize, pageno);
	}

	@GetMapping("{identity}")
	public Customer getCustomerByIdentity(@PathVariable String identity) {
		return customerService.findCustomerByIdentity(identity);
	}

}
