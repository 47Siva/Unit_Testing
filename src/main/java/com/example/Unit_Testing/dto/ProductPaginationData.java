package com.example.Unit_Testing.dto;

import java.util.List;

import com.example.Unit_Testing.entity.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPaginationData {

	private List <Product> product;
	
	private Pagination pagination;
}