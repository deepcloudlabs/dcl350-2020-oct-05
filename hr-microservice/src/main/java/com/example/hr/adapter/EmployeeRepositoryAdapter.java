package com.example.hr.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.Identity;
import com.example.hr.orm.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryAdapter implements EmployeeRepository {
	@Autowired
	private EmployeeEntityRepository repository; // Spring Data JPA

	@Override
	public void save(Employee employee) {
		repository.save(EmployeeEntity.fromEmployee(employee));
	}

	@Override
	public Optional<Employee> findByIdentity(Identity identity) {
		return repository.findById(identity.getValue())
				         .map(EmployeeEntity::toEmployee);
	}

	@Override
	public void remove(Employee employee) {
		repository.deleteById(employee.getIdentity().getValue());
	}

}
