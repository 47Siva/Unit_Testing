package com.example.Unit_Testing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Unit_Testing.common.ResourceNotFoundException;
import com.example.Unit_Testing.entity.Employee;
import com.example.Unit_Testing.repository.EmployeeRepository;
import com.example.Unit_Testing.validator.EmployeeValidator;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeValidator validator;

	public Object save(Employee employee) {
		if (validator.isNull(employee)) {
			return "Name, email, and job are required fields.";
		}
		else if (!validator.isValidEmail(employee.getEmail())) {
			return "Invalid email address format.";
		}
		else if (!validator.isEmailUnique(employee.getEmail())) {
			return "Email address already exists.";
		}
		else if (!validator.isValidName(employee.getName())) {
			return "Invalid Name Place Check The Name";
		}
		else if (!validator.isValidPhoneNumber(employee.getNumber())) {
			return "Invalid Number Place Check The Number";
		}
		employeeRepository.save(employee);
		return "The Employee was successfully saved";
	}

	public Object get(int id) throws ResourceNotFoundException {

		 Optional<Employee> employee = employeeRepository.findById(id);
		 
		 if(employee.isPresent()) {
			 return employee.get() ;
		 }else {
			  throw new ResourceNotFoundException("Employee not found with ID: " + id);
		 }
	}

}
