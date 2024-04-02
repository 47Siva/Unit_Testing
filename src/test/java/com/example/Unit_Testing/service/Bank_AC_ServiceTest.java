package com.example.Unit_Testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Unit_Testing.entity.Bank_AC;
import com.example.Unit_Testing.repository.Bank_Account_Repository;

@ExtendWith(MockitoExtension.class)
public class Bank_AC_ServiceTest {

	@Mock
	public Bank_Account_Repository account_Repository;

	@InjectMocks
	public Bank_Account_Services account_Services;

	@Test
	public void testDeposit() {
		// Arrange
		// mock data
		Bank_AC existingAccount = new Bank_AC();
		existingAccount.setId(1);
		existingAccount.setBalance(1000); // Existing balance

		// Deposit amount
		Bank_AC request = new Bank_AC();
		request.setId(1);
		request.setBalance(500);

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));
//		when(account_Repository.findById(2)).thenReturn(Optional.empty());
		when(account_Repository.save(existingAccount)).thenReturn(existingAccount);

		Object deposit = account_Services.deposit(request);

		// Verify
		verify(account_Repository, times(1)).save(existingAccount);

		// Assertion
		assertNotNull(existingAccount);
//		assertEquals(1500.0, deposit);
		assertEquals("successfully Deposit", ((Map<String, Object>) deposit).get("Message"));
		assertEquals(1500.0, ((Map<String, Object>) deposit).get("Balance"));
//		assertEquals("Account not found", deposit);
	}

	@Test
	public void testWithdrawSufficientFunds() {

		// Arrange
		// mock Data
		Bank_AC existingAccount = new Bank_AC();
		existingAccount.setId(1);
		existingAccount.setBalance(1000);// Existing balance

		Bank_AC request = new Bank_AC();
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
		Bank_AC existingAccount = new Bank_AC();
		existingAccount.setId(1);
		existingAccount.setBalance(1000);// Existing balance

		Bank_AC request = new Bank_AC();
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
		Bank_AC existingAccount = new Bank_AC();
		existingAccount.setId(1);
		existingAccount.setBalance(0);
		
		//Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));
		
		Object result = account_Services.closeAccount(existingAccount.getId());
		
		//Assertion
		assertEquals("Your Account was Closed", result);
	}

}
