package com.example.Unit_Testing.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Unit_Testing.dto.BlanaceCheck;
import com.example.Unit_Testing.entity.Bank_AC;
import com.example.Unit_Testing.service.Bank_Account_Services;

@RestController
@RequestMapping("/api/accountcontroller")
public class Bank_Account_Details_Controller {

	@Autowired
	Bank_Account_Services services;

	@PostMapping("/post")
	public String create(@RequestBody Bank_AC request) {

		services.create(request);

		return "successfully Created";
	}

	@PostMapping("/deposit")
	public Object Deposit(@RequestBody Bank_AC request) throws Exception {

		return services.deposit(request);
	}

	@GetMapping("/checkbalance/{id}")
	public Object checkbalance(@PathVariable("id") int id) {
		return services.checkbalance3(id);
	}

	@PutMapping("/withdraw")
	public Object withdraw(@RequestBody Bank_AC request) throws Exception {

		return services.withdraw(request);
	}

	@DeleteMapping("/closeaccount/{id}")
	public Object closeAccount(@PathVariable("id") int id) {

		return services.closeAccount(id);
	}
	

//	@GetMapping("/get/{id}")
//	public BlanaceCheck checkbalance(@PathVariable("id") int id) {
//		return services.get(id);
//	}
//
//	@GetMapping("/get2/{id}")
//	public Optional<Object> checkbalance2(@PathVariable("id") int id) {
//		return services.get2(id);
//	}

}
