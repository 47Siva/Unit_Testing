package com.example.UnitTesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.UnitTesting.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
