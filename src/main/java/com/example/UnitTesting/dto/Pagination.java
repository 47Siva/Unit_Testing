package com.example.UnitTesting.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {

	private long totalCount;
	
	private int pageSize;
	
	private int totalpage;
	
	private int pageNumber;
	
	private boolean isFirst;
	
	private boolean isLast;
	
	public static <T> Pagination createPagination(Page<T> page) {
		
		Pagination pagination = Pagination.builder()
				                .isFirst(page.isFirst())
				                .isLast(page.isLast())
				                .pageNumber(page.getNumber()+1)
				                .pageSize(page.getSize())
				                .totalCount(page.getTotalElements())
				                .totalpage(page.getTotalPages()).build();
		return pagination;
	}
}
