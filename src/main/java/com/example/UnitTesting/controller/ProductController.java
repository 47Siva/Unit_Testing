package com.example.UnitTesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UnitTesting.entity.Employee;
import com.example.UnitTesting.entity.Product;
import com.example.UnitTesting.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Product")

public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/createall")
	public ResponseEntity<?> post(@RequestBody List<Product> product){
			return productService.listsave(product);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> post(Pageable pagiable ,@RequestParam (value = "field",required = false) String field){
		return productService.getall(pagiable,field);
	}
}
