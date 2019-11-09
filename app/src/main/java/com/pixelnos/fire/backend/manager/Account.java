package com.pixelnos.fire.backend.manager;

import java.util.ArrayList;
import java.util.Comparator;

public class Account {
	public class AccountData {
		public String _name;
		public String _currency;
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

	public AccountData data = new AccountData();
	public BankBook bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());

	public Account(String accountName, String accountCurrency, double accountBalance) {
		data._name = accountName;
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