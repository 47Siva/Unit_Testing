package com.example.Unit_Testing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Unit_Testing.dto.Pagination;
import com.example.Unit_Testing.dto.ProductPaginationData;
import com.example.Unit_Testing.entity.Employee;
import com.example.Unit_Testing.entity.Product;
import com.example.Unit_Testing.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service

public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<?> listsave(List<Product> product) {
		
		List<Product> emp = productRepository.saveAll(product);
		return ResponseEntity.ok(emp);
	}

	public ResponseEntity<?> getall(Pageable pagiable) {
		 
		 Page<Product> page = productRepository.findAll(pagiable);
		
		 Pagination pagination = Pagination.createPagination(page);
		 
		 List<Product> productContent = page.getContent();
		 
		 ProductPaginationData paginationData = ProductPaginationData.builder()
				                                .pagination(pagination)
				                                .product(productContent).build();
		 
		return ResponseEntity.ok(paginationData);
	}

}
