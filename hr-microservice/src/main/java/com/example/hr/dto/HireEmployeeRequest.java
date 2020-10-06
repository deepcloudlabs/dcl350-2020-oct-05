package com.example.hr.dto;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.Identity;
import com.example.hr.orm.EmployeeEntity;

public class HireEmployeeRequest {
	private String identity;
	private String fullname;
	private double salary;
	private String iban;
	private int birthYear;
	private byte[] photo;
	private boolean fulltime;
	private Department department;

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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
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

	@Override
	public String toString() {
		return "HireEmployeeRequest [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban="
				+ iban + ", birthYear=" + birthYear + ", fulltime=" + fulltime + ", department=" + department + "]";
	}
	
	public Employee toEmployee() {
		String []names = this.getFullname().split("\\w+");
		return new Employee.Builder(Identity.valueOf(this.getIdentity()))
		             .fullname(names[0], names[1])
		             .birthYear(this.getBirthYear())
		             .photo(this.getPhoto())
		             .salary(this.getSalary())
		             .iban(this.getIban())
		             .fulltime(this.isFulltime())
		             .department(this.getDepartment())
		             .build();
	}
}
