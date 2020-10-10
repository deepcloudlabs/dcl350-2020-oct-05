package com.example.crm.service;

import java.util.List;

import com.example.crm.document.Customer;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public interface CustomerQueryService {

	List<Customer> findAllCustomers(int pagesize, int pageno);

	Customer findCustomerByIdentity(String identity);

}
