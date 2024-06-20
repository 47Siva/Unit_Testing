package com.example.Unit_Testing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Unit_Testing.entity.Employee;
import com.example.Unit_Testing.entity.Product;
import com.example.Unit_Testing.service.ProductService;

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
	public ResponseEntity<?> post(Pageable pagiable){
		return productService.getall(pagiable);
	}
}
