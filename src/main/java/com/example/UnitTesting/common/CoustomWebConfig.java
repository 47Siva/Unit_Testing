package com.example.UnitTesting.common;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CoustomWebConfig implements WebMvcConfigurer{

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		
//		PageableHandlerMethodArgumentResolverSupport

		PageableHandlerMethodArgumentResolver argumentResolver = new PageableHandlerMethodArgumentResolver();
		
		argumentResolver.setSizeParameterName("page-size");
		argumentResolver.setPageParameterName("page-number");
		argumentResolver.setOneIndexedParameters(true);
//		
		Pageable defaultPageable = PageRequest.of(0, 5);
		argumentResolver.setFallbackPageable(defaultPageable);
		
		
		resolvers.add(argumentResolver);
	}
}
