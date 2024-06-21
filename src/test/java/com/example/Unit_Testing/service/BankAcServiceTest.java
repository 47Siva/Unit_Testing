package com.example.Unit_Testing.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.stereotype.Service;

import com.example.UnitTesting.entity.BankAc;
import com.example.UnitTesting.entity.Employee;
import com.example.UnitTesting.repository.BankAccountRepository;
import com.example.UnitTesting.service.BankAccountService;

@ExtendWith(MockitoExtension.class)
public class BankAcServiceTest {

	@Mock
	public BankAccountRepository account_Repository;

	@InjectMocks
	public BankAccountService account_Services;

	@Test
	public void testDeposit() {
		// Arrange
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setAmount(1000); // Existing balance

		BankAc request = new BankAc();
		request.setId(1);
		request.setAmount(500); // Deposit Amount

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));
		when(account_Repository.save(existingAccount)).thenReturn(existingAccount);

		Object result = account_Services.deposit(request);
		Map<String, Object> deposit = new HashMap<>();
		deposit.put("Message", "successfully Deposit");
		deposit.put("Balance", 1500.0);
		
	    Map <String,Object> deposit2 = new HashMap<>();
	    deposit2.put("Message","amount was not deposit");
	    deposit2.put("Balance", 0);
		
		// Verify
		verify(account_Repository, times(1)).save(existingAccount);

		// Assertion
		assertNotNull(existingAccount);
		assertEquals(deposit, result);
		assertNotEquals(deposit2,result);
		assertEquals("successfully Deposit", ((Map<String, Object>) deposit).get("Message"));
		assertEquals(1500.0, ((Map<String, Object>) deposit).get("Balance"));
		assertFalse(result.equals(deposit2));
		assertTrue(result.equals(deposit));
		assertNotSame(deposit, result);
//		assertThat(result);
		assertSame(result, result);
	}

	@Test
	public void testAccountNotfound() {
		// Arrange
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
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setAmount(1000);// Existing balance

		BankAc request = new BankAc();
		request.setId(1);
		request.setAmount(500);// withdraw Amount

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
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setAmount(1000);// Existing balance

		BankAc request = new BankAc();
		request.setId(1);
		request.setAmount(1500);// withdraw Amount

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
		BankAc existingAccount = new BankAc();
		existingAccount.setId(1);
		existingAccount.setAmount(0);

		// Act
		when(account_Repository.findById(1)).thenReturn(Optional.of(existingAccount));

		Object result = account_Services.closeAccount(existingAccount.getId());

		// Assertion
		assertEquals("Your Account was Closed", result);
	}

}
