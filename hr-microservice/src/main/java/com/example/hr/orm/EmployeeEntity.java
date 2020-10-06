package com.example.hr.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.Identity;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
	@Id
	@Column(name = "tc_kimlik_no")
	private String identity;
	@Column(name = "full_name")
	private String fullname;
	@Column(name = "maas")
	private double salary;
	private String iban; // column: iban
	@Column(name = "birth_year")
	private int birthYear;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	private boolean fulltime;
	@Enumerated(EnumType.STRING)
	private Department department;
	
	public EmployeeEntity() {
	}

	public EmployeeEntity(String identity, String fullname, double salary, String iban, int birthYear, boolean fulltime,
			Department department) {
		this.identity = identity;
		this.fullname = fullname;
		this.salary = salary;
		this.iban = iban;
		this.birthYear = birthYear;
		this.fulltime = fulltime;
		this.department = department;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeEntity other = (EmployeeEntity) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban="
				+ iban + ", birthYear=" + birthYear + ", fulltime=" + fulltime + ", department=" + department + "]";
	}
	
	public Employee toEmployee() {
		String []names = fullname.split("\\s+");
		return new Employee.Builder(Identity.valueOf(this.identity))
		             .fullname(names[0], names[1])
		             .birthYear(this.birthYear)
		             .photo(this.photo)
		             .salary(this.salary)
		             .iban(this.iban)
		             .fulltime(this.fulltime)
		             .department(this.department)
		             .build();
	}
	
	public static EmployeeEntity fromEmployee(Employee employee) {
		String identity = employee.getIdentity().getValue();
		String fullname = employee.getFullname().getFirst() + " " + employee.getFullname().getLast();
		double salary = employee.getSalary().getValue();
		String iban = employee.getIban().getValue();
		int birthYear = employee.getBirthYear().getValue();
		boolean fulltime = employee.isFulltime();
		Department department = employee.getDepartment();
		EmployeeEntity entity = new EmployeeEntity(identity, fullname, salary, iban, birthYear, fulltime, department);
		entity.setPhoto(employee.getPhoto().getValues());
		return entity;
	}

}
