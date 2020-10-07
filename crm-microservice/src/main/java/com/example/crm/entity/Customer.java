package com.example.crm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	@Id
	private String identity;
	@Column(name= "full_name")
	private String fullname;
	@Column(name= "home_address")
	private String homeAddress;
	@Column(name= "business_address")
	private String businessAddress;
	private String email;
	private String sms;
	@Column(name= "birth_year")
	private int birthYear;
	private byte[] photo;
	
}
