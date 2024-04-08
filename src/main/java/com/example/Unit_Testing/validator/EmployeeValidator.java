package com.example.Unit_Testing.validator;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Unit_Testing.entity.Employee;
import com.example.Unit_Testing.repository.EmployeeRepository;

@Component
public class EmployeeValidator {

	@Autowired
	private EmployeeRepository employeeRepository;

	public boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		return Pattern.matches(emailRegex, email);
	}

	public boolean isEmailUnique(String email) {
		Optional<Employee> employeesWithEmail = employeeRepository.findByEmail(email);
		return employeesWithEmail.isEmpty();
	}

	public boolean isValidName(String name) {
		if (name == null || name.trim().isEmpty()) {
			return false;
		}
		if (!Character.isUpperCase(name.charAt(0))) {
			return false;
		}
		return name.length() >= 2 && name.length() <= 50;
	}

	public boolean isValidPhoneNumber(long phoneNumber) {
		String phoneNumberString = Long.toString(phoneNumber);

		if (phoneNumberString.length() != 10) {
			return true;
		}
		if (!Pattern.matches("\\d{10}", phoneNumberString)) {
			return true;
		}
		String regex = "^\\+(91)[0-9]{10}$";
		return Pattern.matches(regex, phoneNumberString);
	}

	public boolean isNull(Employee employee) {
		return employee.getName().isEmpty() || employee.getEmail().isEmpty() || employee.getJob().isEmpty();
	}
}
