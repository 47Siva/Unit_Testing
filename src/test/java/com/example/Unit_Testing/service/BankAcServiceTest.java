package com.example.Unit_Testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Unit_Testing.entity.BankAc;
import com.example.Unit_Testing.repository.BankAccountRepository;

@ExtendWith(MockitoExtension.class)
public class BankAcServiceTest {

	@Mock
	public BankAccountRepository account_Repository;

	@InjectMocks
	public BankAccountServices account_Services;

	@Test
	public void testDeposit() {
		// Arrange
		// mock data
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setBalance(1000); // Existing balance

		// Deposit amount
		BankAc request = new BankAc();
		request.setId(1);
		request.setBalance(500); // Deposit Amount

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));
		when(account_Repository.save(existingAccount)).thenReturn(existingAccount);

		Object deposit = account_Services.deposit(request);
		Map<String, Object> result = new HashMap<>();
		result.put("Message", "successfully Deposit");
		result.put("Balance", 1500.0);
		
		// Verify
		verify(account_Repository, times(1)).save(existingAccount);

		// Assertion
		assertNotNull(existingAccount);
		assertEquals(deposit, result);
		assertEquals("successfully Deposit", ((Map<String, Object>) deposit).get("Message"));
		assertEquals(1500.0, ((Map<String, Object>) deposit).get("Balance"));
	}

	@Test
	public void testAccountNotfound() {
		// Arrange
		// mock data
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.empty());

		Object account = account_Services.deposit(existingAccount);

		// Assertion
		assertEquals("Account not found", account);
	}

	@Test
	public void testWithdrawSufficientFunds() {

		// Arrange
		// mock Data
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setBalance(1000);// Existing balance

		BankAc request = new BankAc();
		request.setId(1);
		request.setBalance(500);// withdraw Amount

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));
		when(account_Repository.save(existingAccount)).thenReturn(existingAccount);

		Object withdraw = account_Services.withdraw(request);

		// verify
		verify(account_Repository, times(1)).save(existingAccount);

		// Assertion
		assertNotNull(existingAccount);
		assertEquals("successfully withdraw", ((Map<String, Object>) withdraw).get("Message"));
		assertEquals(500.0, ((Map<String, Object>) withdraw).get("Balance"));
	}

	@Test
	public void testWithdrawInsufficientFunds() {
		// Arrange
		// mock Data
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setBalance(1000);// Existing balance

		BankAc request = new BankAc();
		request.setId(1);
		request.setBalance(1500);// withdraw Amount

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));

		Object withdraw = account_Services.withdraw(request);

		// Assertion
		assertNotNull(existingAccount);
		assertEquals("Insufficient balance", withdraw);
	}

	@Test
	public void testCloseAccount() {
		// Arrange
		// mock Data
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setBalance(0);

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));

		Object result = account_Services.closeAccount(existingAccount.getId());

		// Assertion
		assertEquals("Your Account was Closed", result);
	}

}
