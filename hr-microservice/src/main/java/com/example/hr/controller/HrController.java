package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Identity;
import com.example.hr.dto.FireEmployeeResponse;
import com.example.hr.dto.HireEmployeeRequest;
import com.example.hr.dto.HireEmployeeResponse;

@RestController
@RequestScope
@RequestMapping("employees")
@CrossOrigin
public class HrController {
	@Autowired
	private HrApplication hrApplication;
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(
			@RequestBody HireEmployeeRequest request){
		hrApplication.hireEmployee(request.toEmployee());
		return new HireEmployeeResponse("success");
	}
	
	
	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable String identity){
		var employee = hrApplication.fireEmployee(Identity.valueOf(identity));
		if (employee.isEmpty())
			return new FireEmployeeResponse("failed");
		return new FireEmployeeResponse("success");
	}
	
	
}
