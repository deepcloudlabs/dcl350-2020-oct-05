package com.example.crm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.crm.validation.TcKimlikNo;

public class CustomerRequest {
	@TcKimlikNo
	private String identity;
	@NotNull
	@Size(min = 5)
	private String fullname;
	@Size(min = 12)
	private String homeAddress;
	@Size(min = 12)
	private String businessAddress;
	@Email
	private String email;
	@Pattern(regexp = "^[0-9]{7,12}$")
	private String sms;
	@Max(2020)
	private int birthYear;
	private String photo;

	public CustomerRequest() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
