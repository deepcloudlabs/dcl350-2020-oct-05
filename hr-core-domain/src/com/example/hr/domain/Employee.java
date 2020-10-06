package com.example.hr.domain;

// Entity: Have identity, Mutable Class
// Entity Root -> Aggregate
public class Employee {
	private final Identity identity;
	private Fullname fullname;
	private Money salary;
	private Iban iban;
	private BirthYear birthYear;
	private Photo photo;
	private boolean fulltime;
	private Department department;
	// Builder Pattern
	public Fullname getFullname() {
		return fullname;
	}
	public void setFullname(Fullname fullname) {
		this.fullname = fullname;
	}
	public Money getSalary() {
		return salary;
	}
	public void setSalary(Money salary) {
		this.salary = salary;
	}
	public Iban getIban() {
		return iban;
	}
	public void setIban(Iban iban) {
		this.iban = iban;
	}
	public BirthYear getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public boolean isFulltime() {
		return fulltime;
	}
	public void setFulltime(boolean fulltime) {
		this.fulltime = fulltime;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Identity getIdentity() {
		return identity;
	}
	
}
