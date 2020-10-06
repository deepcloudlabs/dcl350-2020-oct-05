package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.Identity;
import com.example.hr.events.EmployeeFiredEvent;
import com.example.hr.events.EmployeeHiredEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class SimpleHrApplication implements HrApplication {

	private EmployeeRepository employeeRepository;
	private EventPublisher eventPublisher;

	public SimpleHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public boolean hireEmployee(Employee employee) {
		// Invariant
		employeeRepository.save(employee);
		eventPublisher.publishEvent(new EmployeeHiredEvent("employees",employee));
		return true;
	}

	@Override
	public Optional<Employee> fireEmployee(Identity identity) {
		Optional<Employee> employee = employeeRepository.findByIdentity(identity);
		if (employee.isPresent()) {
			Employee emp = employee.get();
			employeeRepository.remove(emp);
			eventPublisher.publishEvent(new EmployeeFiredEvent("employees",emp));
		}
		return employee;
	}

}
