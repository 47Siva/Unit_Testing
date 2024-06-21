package com.example.UnitTesting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.UnitTesting.dto.Pagination;
import com.example.UnitTesting.dto.ProductPaginationData;
import com.example.UnitTesting.entity.Product;
import com.example.UnitTesting.repository.ProductRepository;

@Service

public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<?> listsave(List<Product> product) {
		
		List<Product> emp = productRepository.saveAll(product);
		return ResponseEntity.ok(emp);
	}

	public ResponseEntity<?> getall(Pageable pagiable, String field) {
		 
		String defaultSortField = "id"; // Replace with your default field
//		
		Sort.Direction direction = Sort.Direction.ASC;
		
        String sortField = (field == null || field.isEmpty()) ? defaultSortField : field;
	    Pageable sortedPageable = PageRequest.of(pagiable.getPageNumber(), pagiable.getPageSize(), Sort.by(direction, sortField));
	    
	    Page<Product> page = productRepository.findAll(sortedPageable);
		 Pagination pagination = Pagination.createPagination(page);
//		 
		 List<Product> productContent = page.getContent();
//		 
		 ProductPaginationData paginationData = ProductPaginationData.builder()
				                                .pagination(pagination)
				                                .product(productContent).build();
		 
		return ResponseEntity.ok(paginationData);
	}

}
