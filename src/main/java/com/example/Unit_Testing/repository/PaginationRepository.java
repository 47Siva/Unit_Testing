package com.example.Unit_Testing.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.Unit_Testing.entity.Employee;

@Repository
public interface PaginationRepository extends PagingAndSortingRepository<Employee, Integer>{

}
