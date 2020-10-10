package com.example.crm.service;

import java.util.List;
import java.util.Map;

import com.example.crm.document.Customer;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CustomerService {

	List<Customer> findAllCustomers(int pagesize, int pageno);

	Customer findCustomerByIdentity(String identity);

	Customer createCustomer(Customer customer);

	Customer updateCustomerByIdentity(String identity, Customer customer);

	Customer updateCustomerByIdentity(String identity, Map<String, Object> request);

	Customer deleteCustomerByIdentity(String identity);

}
