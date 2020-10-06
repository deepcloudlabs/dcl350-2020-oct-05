package com.example.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.SimpleHrApplication;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
	public HrApplication application(EmployeeRepository employeeRepository,
			   EventPublisher eventPublisher) {
		System.err.println("Creating SimpleHrApplication Bean...");
		return new SimpleHrApplication(employeeRepository, eventPublisher);
	}
}
