package com.pixelnos.fire.backend.manager;

import java.util.ArrayList;
import java.util.Comparator;

public class Account {
	private final String _name;
	private AccountData data = new AccountData();
	private BankBook bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());

	public class AccountData {
		public Currency _currency;
		public double _balance;
		public double _initialBalance;
	}

	public class BankBook {
		public ArrayList<Transaction> debits;
		public ArrayList<Transaction> credits;
		public BankBook(ArrayList<Transaction> debits, ArrayList<Transaction> credits) {
			this.debits = debits;
			this.credits = credits;
		}
	}



	public Account(String accountName, Currency accountCurrency, double accountBalance) {
		_name = accountName;
		data._currency = accountCurrency;
		data._balance =  accountBalance;
		data._initialBalance =  accountBalance;
	}
	
	public void addDebit(Transaction debit) {
		bankBook.debits.add(debit);
		updateAccountBalance(-debit._amount);
	}
	public void addDebits(ArrayList<Transaction> debits) {
		for(Transaction debit : debits){
			bankBook.debits.add(debit);
			updateAccountBalance(-debit._amount);
		}
	}
	public void addCredit(Transaction credit) {
		bankBook.credits.add(credit);
		updateAccountBalance(credit._amount);
	}
	public void addCredits(ArrayList<Transaction> credits) {
		for(Transaction credit : credits){
			bankBook.credits.add(credit);
			updateAccountBalance(credit._amount);
		}
	}

	public AccountData getData(){
		return data;
	}
	public String getName(){
		return _name;
	}

	public BankBook getBankBook(){
		return bankBook;
	}

	private void updateAccountBalance(double amount) {
		data._balance +=amount;
	}
	
	public void resetAccount() {
		bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());
		data._balance = data._initialBalance;
	}
}

class TransactionSorter implements Comparator<Transaction>{
	@Override
	public int compare(Transaction t1, Transaction t2) {
		return t1._date.compareTo(t2._date);
	}

}