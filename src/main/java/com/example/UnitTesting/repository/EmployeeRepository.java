package com.example.UnitTesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.UnitTesting.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "select * from employee where email =:email", nativeQuery = true)
	Optional<Employee> findByEmail(String email);

}
