package com.example.crm.service.business;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.LockModeType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;
import com.example.crm.entity.Customer;
import com.example.crm.repository.CustomerRepository;
import com.example.crm.service.CustomerService;

@Service
public class SimpleCustomerService implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CustomerResponse findById(String identity) {
		Optional<Customer> cust = customerRepository.findById(identity);
		if (cust.isEmpty())
			throw new IllegalArgumentException("Cannot find customer");
		return modelMapper.map(cust.get(), CustomerResponse.class);
	}

	@Override
	public List<CustomerResponse> findAllCustomers(int page, int size) {
		return customerRepository.findAll(PageRequest.of(page, size))
				.getContent()
				.stream()
				.map( cust -> modelMapper.map(cust, CustomerResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	@Lock(LockModeType.PESSIMISTIC_READ)
	public CustomerResponse add(CustomerRequest customerRequest) {
		Customer customer = modelMapper.map(customerRequest, Customer.class);
		customerRepository.save(customer);
		return modelMapper.map(customer, CustomerResponse.class);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER, isolation = Isolation.READ_COMMITTED)
	public CustomerResponse update(String identity, CustomerRequest customerRequest) {
		var cust = customerRepository.findById(identity);
		if (cust.isEmpty())
			throw new IllegalArgumentException("Cannot find customer to update");
		var customer = cust.get();
		if (Objects.isNull(customerRequest.getPhoto()))
			customer.setPhoto(null);
		else	
		   customer.setPhoto(customerRequest.getPhoto().getBytes());
		customer.setHomeAddress(customerRequest.getHomeAddress());
		customer.setBusinessAddress(customerRequest.getBusinessAddress());
		customer.setSms(customerRequest.getSms());
		// customerRepository.save(customer); // JPA -> Bulk Operations
		// customerRepository.flush();
		return modelMapper.map(customer, CustomerResponse.class);
	}

	@Override
	@Transactional
	public CustomerResponse update(String identity, Map<String, Object> customerPatchRequest) {
		var cust = customerRepository.findById(identity);
		if (cust.isEmpty())
			throw new IllegalArgumentException("Cannot find customer to update");
		var customer = cust.get();
        for (var entry : customerPatchRequest.entrySet()) {
        	Field field;
			try {
				field = Customer.class.getDeclaredField(entry.getKey());
				if (Objects.nonNull(field)) {
				   	field.setAccessible(true);
				   	field.set(customer, entry.getValue());
				   	field.setAccessible(false);
				}
			} catch (Exception e) {
				System.err.println("Reason: "+e.getMessage());
			}
        }
		return modelMapper.map(customer, CustomerResponse.class);
	}

	@Override
	@Transactional
	public CustomerResponse deleteById(String identity) {
		Optional<Customer> cust = customerRepository.findById(identity);
		if (cust.isEmpty())
			throw new IllegalArgumentException("Cannot find customer to delete");
		var customer = cust.get();
		customerRepository.delete(customer);
		return modelMapper.map(customer, CustomerResponse.class);
	}

}
