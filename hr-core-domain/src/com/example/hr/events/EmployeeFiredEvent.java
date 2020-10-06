package com.example.hr.events;

import com.example.hr.domain.Employee;

public class EmployeeFiredEvent extends BusinessEvent {
	
	private final Employee employee;

	public EmployeeFiredEvent(String topic, Employee employee) {
		super(topic);
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	public String toString() {
		return "EmployeeFiredEvent [employee identity=" + employee.getIdentity() + ", topic=" + topic + ", id=" + id + "]";
	}
	
	

}
