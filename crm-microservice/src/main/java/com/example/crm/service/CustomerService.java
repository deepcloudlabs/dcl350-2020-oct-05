package com.example.crm.service;

import java.util.List;
import java.util.Map;

import com.example.crm.dto.CustomerRequest;
import com.example.crm.dto.CustomerResponse;

public interface CustomerService {

	CustomerResponse findById(String identity);

	List<CustomerResponse> findAllCustomers(int page, int size);

	CustomerResponse add(CustomerRequest customerRequest);

	CustomerResponse update(String identity, CustomerRequest customerRequest);

	CustomerResponse update(String identity, Map<String, Object> customerPatchRequest);

	CustomerResponse deleteById(String identity);

}
