package com.example.Unit_Testing.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Unit_Testing.entity.BankAc;
import com.example.Unit_Testing.repository.BankAccountRepository;

@Service
public class BankAccountServices {

	@Autowired
	BankAccountRepository repository;

	public BankAc create(BankAc request) {

		return repository.save(request);
	}

	public Object checkbalance3(int id) {

		Optional<Object> balance = repository.findbalance(id);
		Optional<BankAc> Account = repository.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (Account.isPresent()) {
			response.put("balance", balance);
			return response;
		} else {
			response.put("Message", " Account not found");
			return response;
		}
	}

	public Object deposit(BankAc request)  {
		Optional<BankAc> account = repository.findById(request.getId());

		Map<String, Object> response = new HashMap<>();
		if (account.isPresent()) {
			BankAc Ac = account.get();
			Ac.setBalance(Ac.getBalance() + request.getBalance());
			repository.save(Ac);

			response.put("Message", "successfully Deposit");
			response.put("Balance", Ac.getBalance());
			return response;
		} else {
			return "Account not found";
		}

	}

	public Object withdraw(BankAc request) {

		Optional<BankAc> account = repository.findById(request.getId());

		Map<String, Object> response = new HashMap<>();

		if (account.isPresent()) {
			BankAc Ac = account.get();
			if (Ac.getBalance() < request.getBalance()) {
				return "Insufficient balance";
			} else {
				Ac.setBalance(Ac.getBalance() - request.getBalance());
				repository.save(Ac);
				response.put("Message", "successfully withdraw");
				response.put("Balance", Ac.getBalance());
				return response;
			}
		} else {
			return "Account not found";
		}

	}

	public Object closeAccount(int id) {

		Map<String, Object> response = new HashMap<>();
		Optional<BankAc> account = repository.findById(id);

		if (account.isPresent()) {
			BankAc Ac = account.get();
			if (Ac.getBalance() == 0) {
				repository.deleteById(id);
				return "Your Account was Closed";
			} else {
				return "Your Account was not closed becouse your balance amount now standing your account";
			}

		} else {
			return "Account not fount";
		}
	}

//	public BlanaceCheck get(int id) {
//	return repository.findBybalance(id);
//}
//
//public Optional<Object> get2(int id) {
//
//	return repository.findbalance(id);
//}
}
