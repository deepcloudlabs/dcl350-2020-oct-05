package com.example.crm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import com.example.crm.service.CustomerService;
import com.example.crm.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("customers")
@CrossOrigin
@Validated
public class CrmController { // Adapter
	@Autowired
	private CustomerService customerService;

	// GET http://localhost:8200/crm/api/v1/customers/1111111110
	@GetMapping("{identity}")
	public CustomerResponse findCustomerByIdentity(
			@PathVariable 
			@TcKimlikNo String identity) {
		return customerService.findById(identity);
	}
	
	// GET http://localhost:8200/crm/api/v1/customers?page=10&size=25
	@GetMapping(params = {"page", "size"})
	public List<CustomerResponse> findBypagination(
			@RequestParam(required = false, defaultValue = "0") 
			@Min(0) int page,
			@RequestParam(required = false, defaultValue = "25") 
			@Max(50) int size) {
		return customerService.findAllCustomers(page,size);
	}
	
    @PostMapping
    public CustomerResponse addCustomer(
    		@RequestBody  @Validated CustomerRequest customerRequest) {
    	return customerService.add(customerRequest);
    }
    
    @PutMapping("{identity}")
    //@Transactional(rollbackFor = IllegalArgumentException.class)
    public CustomerResponse updateCustomer(
    		@RequestBody @Validated CustomerRequest customerRequest,
    		@PathVariable @TcKimlikNo String identity) {
    	return customerService.update(identity,customerRequest);
    }
    
    @PatchMapping("{identity}")
    public CustomerResponse patchCustomer(@RequestBody Map<String,Object> customerPatchRequest,
    		@PathVariable @TcKimlikNo String identity) {
    	return customerService.update(identity,customerPatchRequest);
    }
    
	@DeleteMapping("{identity}")
	public CustomerResponse removeCustomerByIdentity(
			@PathVariable @TcKimlikNo String identity) {
		return customerService.deleteById(identity);
	}
}
