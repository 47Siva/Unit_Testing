package com.example.Unit_Testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Unit_Testing.entity.BankAc;
import com.example.Unit_Testing.service.BankAccountService;

@RestController
@RequestMapping("/api/accountcontroller")
public class BankAccountDetailsController {

	@Autowired
	BankAccountService services;

	@PostMapping("/post")
	public String create(@RequestBody BankAc request) {

		services.create(request);

		return "successfully Created";
	}

	@PostMapping("/deposit")
	public Object Deposit(@RequestBody BankAc request) throws Exception {

		return services.deposit(request);
	}

	@GetMapping("/checkbalance/{id}")
	public Object checkbalance(@PathVariable("id") int id) {
		return services.checkbalance3(id);
	}

	@PutMapping("/withdraw")
	public Object withdraw(@RequestBody BankAc request) throws Exception {

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
