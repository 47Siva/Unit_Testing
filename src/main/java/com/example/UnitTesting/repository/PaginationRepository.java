package com.example.UnitTesting.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.UnitTesting.entity.Employee;

@Repository
public interface PaginationRepository extends PagingAndSortingRepository<Employee, Integer>{

}
