package com.example.hr.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hr.domain.Department;
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
		repository.save(mapEmployeeToEmployeeEntity(employee));
	}

	@Override
	public Optional<Employee> findByIdentity(Identity identity) {
		var entity = repository.findById(identity.getValue());
		if (entity.isEmpty())
			return Optional.empty();
		return Optional.of(mapEmployeeEntityToEmployee(entity.get()));
	}

	@Override
	public void remove(Employee employee) {
		repository.deleteById(employee.getIdentity().getValue());
	}

	private EmployeeEntity mapEmployeeToEmployeeEntity(Employee employee) {
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

	private Employee mapEmployeeEntityToEmployee(EmployeeEntity entity) {
		String []names = entity.getFullname().split("\\s+");
		return new Employee.Builder(Identity.valueOf(entity.getIdentity()))
		             .fullname(names[0], names[1])
		             .birthYear(entity.getBirthYear())
		             .photo(entity.getPhoto())
		             .salary(entity.getSalary())
		             .iban(entity.getIban())
		             .fulltime(entity.isFulltime())
		             .department(entity.getDepartment())
		             .build();
	}
}
