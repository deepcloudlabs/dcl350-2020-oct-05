package com.example.hr.repository;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.Identity;

public interface EmployeeRepository {

	void save(Employee employee);

	Optional<Employee> findByIdentity(Identity identity);

	void remove(Employee employee);

}
