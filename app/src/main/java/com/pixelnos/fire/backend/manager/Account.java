package com.pixelnos.fire.backend.manager;

import java.util.ArrayList; 

public class Account {
	
	public static class AccountData {
		public String _name;
		public String _currency;
		public double _balance;
		public double _initialBalance;
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

	public Account(String name, String currency, double balance) {
		data._name = name;
		data._currency = currency;
		data._balance =  balance;
		data._initialBalance =  balance;
	}
	
	public void addDebit(Transaction expense) {
		bankBook.debits.add(expense); 
		updateAccountBalance(-expense._amount);
	}
	
	public void addCredit(Transaction credit) {
		bankBook.credits.add(credit);
		updateAccountBalance(credit._amount);
	}
	
	private void updateAccountBalance(double amount) {
		data._balance +=amount;
	}
	
	public void resetAccount() {
		bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());
		data._balance = data._initialBalance;
	}  
	
	
	
}
