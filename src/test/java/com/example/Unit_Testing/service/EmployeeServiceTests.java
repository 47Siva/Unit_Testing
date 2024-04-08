package com.example.Unit_Testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Unit_Testing.common.ResourceNotFoundException;
import com.example.Unit_Testing.entity.Employee;
import com.example.Unit_Testing.repository.EmployeeRepository;
import com.example.Unit_Testing.validator.EmployeeValidator;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeService service;

	@Mock
	EmployeeValidator validator;

	@Test
	public void createEmployeeTest() {
		Employee employee = new Employee(1, "Siva", "devloper", 912345678901l, 15000.23, "Siva@123gmail.com");
		when(employeeRepository.save(employee)).thenReturn(employee);
		lenient().when(validator.isNull(employee)).thenReturn(false);
		lenient().when(validator.isValidEmail(employee.getEmail())).thenReturn(true);
		lenient().when(validator.isEmailUnique(employee.getEmail())).thenReturn(true);
		lenient().when(validator.isValidName(employee.getName())).thenReturn(true);
		lenient().when(validator.isValidPhoneNumber(employee.getNumber())).thenReturn(true);
		Object result = service.save(employee);
		verify(employeeRepository, times(1)).save(employee);
		assertEquals("The Employee was successfully saved", result);

		Employee employee1 = new Employee(1, "Siva", "", 912345678901l, 15000.23, "Siva@123gmail.com");
		lenient().when(validator.isNull(employee1)).thenReturn(true);
		Object result1 = service.save(employee1);
		assertEquals("Name, email, and job are required fields.", result1);

		Employee employee2 = new Employee(1, "Siva", "devloper", 912345678901l, 15000.23, "Sivagmail.com");
		lenient().when(validator.isValidEmail(employee2.getEmail())).thenReturn(false);
		Object result2 = service.save(employee2);
		assertEquals("Invalid email address format.", result2);

		Employee employee3 = new Employee(1, "Siva", "devloper", 912345678901l, 15000.23, "Siva@123gmail.com");
		lenient().when(validator.isEmailUnique(employee3.getEmail())).thenReturn(false);
		Object result3 = service.save(employee3);
		assertEquals("Email address already exists.", result3);

		Employee employee4 = new Employee(1, "", "devloper", 9145678901l, 15000.23, "Siva@123gmail.com");
		lenient().when(validator.isEmailUnique(employee4.getEmail())).thenReturn(true);
		lenient().when(validator.isValidName(employee4.getName())).thenReturn(false);
		Object result4 = service.save(employee4);
		assertEquals("Invalid Name Place Check The Name", result4);

		Employee employee5 = new Employee(1, "Siva", "devloper", 2345678901l, 15000.23, "Siva@123gmail.com");
		lenient().when(validator.isValidPhoneNumber(employee5.getNumber())).thenReturn(false);
		Object result5 = service.save(employee5);
		assertEquals("Invalid Number Place Check The Number", result5);
	}

	@Test
	public void getEmployeeTest() {
		// Arrange
		int employeeId = 1;
		Employee employee = new Employee(1, "Siva", "devloper", 912345678901l, 15000.23, "Siva@123gmail.com");
		// Act
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		Object result = service.get(employeeId);
		// Assertion
		assertEquals(employee, result);
	}

	@Test
    public void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(2)).thenReturn(Optional.empty());
        Exception exception = null;
        try {
            service.get(2);
        } catch (ResourceNotFoundException ex) {
            exception = ex;
        }
        assertEquals("Employee not found with ID: 2", exception.getMessage());
    }
}
