package com.example.UnitTesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UnitTesting.entity.Employee;
import com.example.UnitTesting.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/create")
	public Object save(@RequestBody Employee employee) {
		return employeeService.save(employee);
	}
	
	@PostMapping("/createall")
	public ResponseEntity<?> post(@RequestBody List<Employee> employee){
			return employeeService.listsave(employee);
	}
	
	@GetMapping("/get/{id}")
	public Object get(@PathVariable ("id") int id) {
		return employeeService.get(id);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(Pageable pageable) {
		return employeeService.getAll(pageable);
	}
	
}
