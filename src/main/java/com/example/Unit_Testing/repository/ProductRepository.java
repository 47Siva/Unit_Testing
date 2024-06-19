package com.example.Unit_Testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Unit_Testing.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}