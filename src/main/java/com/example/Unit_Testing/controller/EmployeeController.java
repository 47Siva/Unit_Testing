package com.example.Unit_Testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Unit_Testing.entity.Employee;
import com.example.Unit_Testing.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/create")
	public Object save(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	
	@GetMapping("/get/{id}")
	public Object get(@PathVariable ("id") int id) {
		return employeeService.get(id);
	}
}
