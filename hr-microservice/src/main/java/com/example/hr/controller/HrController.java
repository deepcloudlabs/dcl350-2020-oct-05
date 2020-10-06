package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.application.HrApplication;

@RestController
@RequestScope
@RequestMapping("employees")
@CrossOrigin
public class HrController {
	@Autowired
	private HrApplication hrApplication;
	
	@PostMapping
	public void hireEmployee(){
		
	}
	
	
	@DeleteMapping("{identity}")
	public void fireEmployee(@PathVariable String identity){
		
	}
}
