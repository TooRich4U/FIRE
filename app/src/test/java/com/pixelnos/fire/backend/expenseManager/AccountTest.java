package com.pixelnos.fire.backend.expenseManager;

import static org.junit.Assert.assertEquals; 

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

public class AccountTest {
	
	public String accountNameAtTest = "MyFirstAccount";
	public String accountCurrencyAtTest = "EUR";
	public double accountBalanceAtTest = 1000;
	Account account = new Account(accountNameAtTest,accountCurrencyAtTest,accountBalanceAtTest);
	
	@Test
	public void createAccount() {
		assertEquals(accountNameAtTest, account.data.name);;
		assertEquals(accountCurrencyAtTest,account.data.currency);
		assertEquals(Math.abs(accountBalanceAtTest),account.data.balance,0.001);
		assertEquals(Math.abs(accountBalanceAtTest),account.data.balance,0.001); 
	}
	@Test
	public void addExpense() {
		String newLocation = "Paris";
		Date newDate = new Date(); 
		double expenseAmount = 300; 
		ArrayList <String> description = new ArrayList<String>();
		description.add("Utilities");
		description.add("Food");
		Transaction expense = new Transaction(expenseAmount, newLocation, newDate, description);
		account.addDebit(expense);
		assertEquals(expense, account.bankBook.debits.get(0));
		assertEquals(accountBalanceAtTest-expenseAmount,account.data.balance,0.001);
		assertEquals(accountBalanceAtTest,account.data.initialBalance,0.001); 
	}
	@Test
	public void resetAccount() {
		ArrayList <String> description = new ArrayList<String>();
		description.add("Utilities");
		description.add("Food");
		Transaction expense = new Transaction(100, "", null, null);
		account.addDebit(expense);
		account.addDebit(expense);
		account.addDebit(expense);
		account.resetAccount();
		assertEquals(accountBalanceAtTest,account.data.balance,0.01); 
		
		
	}
	 
	
	@Test
	public void addBalance() {
		ArrayList <String> descriptions = new ArrayList<String>();
		descriptions.add("Something");
		double creditedBalance = 100;
		Transaction credit = new Transaction(100,"Place", new Date(),descriptions);
		account.addCredit(credit);
		assertEquals(accountBalanceAtTest+creditedBalance,account.data.balance,0.001);
		assertEquals(account.data.balance-creditedBalance, account.data.initialBalance,0.001); 
	}
	
	@Test
	public void getCurrentBalance()
	{
		ArrayList <String> descriptions = new ArrayList<String>();
		descriptions.add("Something");
		descriptions.add("Food"); 
		account.addDebit(new Transaction(100, "", new Date(), descriptions)); 
		account.addDebit(new Transaction(100, "", new Date(), descriptions)); 
		assertEquals(accountBalanceAtTest - (100 *2), account.data.balance,0.001);
	}
 


	 
	 
}

