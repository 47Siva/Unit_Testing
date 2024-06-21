package com.example.UnitTesting.dto;

import java.util.List;

import com.example.UnitTesting.entity.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPaginationData {

	private List <Product> product;
	
	private Pagination pagination;
}