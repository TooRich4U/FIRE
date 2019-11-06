package com.pixelnos.fire.backend.expenseManager;

import java.util.ArrayList; 

public class Account {
	
	public static class AccountData {
		public String name;
		public String currency;
		public double balance;
		public double initialBalance; 
		public AccountData() { }
	}
	public static class BankBook {
		public ArrayList<Transaction> debits;
		public ArrayList<Transaction> credits; 
		public BankBook(ArrayList<Transaction> debits, ArrayList<Transaction> credits) {
			this.debits = debits;
			this.credits = credits;
		}
	}
	public AccountData data = new AccountData();
	public BankBook bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());

	public Account(String accountName, String accountCurrency, double accountBalance) {
		data.name = accountName;
		data.currency = accountCurrency;
		data.balance =  accountBalance;
		data.initialBalance =  accountBalance; 
	}
	
	public void addDebit(Transaction expense) {
		bankBook.debits.add(expense); 
		updateAccountBalance(-expense.amount);
	}
	
	public void addCredit(Transaction credit) {
		bankBook.credits.add(credit);
		updateAccountBalance(credit.amount); 
	}
	
	private void updateAccountBalance(double amount) {
		data.balance +=amount;
	}
	
	public void resetAccount() {
		bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());
		data.balance = data.initialBalance;
	}  
	
	
	
}
