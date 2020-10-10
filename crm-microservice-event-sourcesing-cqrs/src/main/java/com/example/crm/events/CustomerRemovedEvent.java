package com.example.crm.events;

import com.example.crm.document.Customer;

public class CustomerRemovedEvent extends CustomerBaseEvent {
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerRemovedEvent [customer=" + customer + "]";
	}
}
