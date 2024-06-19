package com.example.Unit_Testing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Unit_Testing.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Product")
@RequiredArgsConstructor
public class ProductController {
	
	ProductService productService;
	
}
