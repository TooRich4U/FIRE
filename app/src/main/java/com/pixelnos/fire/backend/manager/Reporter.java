package com.pixelnos.fire.backend.manager;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class Reporter{
	public Account account;
	
	public Reporter(Account reportAccount) {
		account = reportAccount;
	}

	public ArrayList<Transaction> getDebitTransactionByWeekDay(int weekDayToSearch) {
		ArrayList<Transaction> searchedTransactions = new  ArrayList<>();
		Calendar transactionDate = Calendar.getInstance();
		for(Transaction transaction : account.bankBook.debits ) {
			transactionDate.setTime(transaction._date);
			if(transactionDate.get(Calendar.DAY_OF_WEEK) == weekDayToSearch) searchedTransactions.add(transaction);
		}
		return searchedTransactions;
	}

	public ArrayList<Transaction> getTransactionFromDay(Calendar dateToLookFor) {
		ArrayList<Transaction> searchedTransactions = new  ArrayList<>();
		Calendar transactionDate = Calendar.getInstance();
		for(Transaction transaction : account.bankBook.debits) {
			transactionDate.setTime(transaction._date);
			if(transactionDate.after(dateToLookFor)) searchedTransactions.add(transaction);
		}
		return searchedTransactions;
	}

	public ArrayList<Transaction> getExpensesByDescription(String searchedTag)
	{
		ArrayList <Transaction> searchedExpense = new ArrayList<>();
		for (Transaction debit : account.bankBook.debits) { 
			for (String debitString : debit._tags)
				if (debitString.equals(searchedTag)) searchedExpense.add(debit);   
		}
		return searchedExpense;
	}

	public ArrayList<String> getCurrentExpensesTags() {
		ArrayList <String> tags = new ArrayList<>();
		for(Transaction expense: account.bankBook.debits){
			tags.addAll(expense._tags);
		}
		Set <String>uniqueTags = new HashSet<>(tags);
		return (new ArrayList<>(uniqueTags));
	}
	 
	public Map<String,Long> getTagsUsageCount() {
		ArrayList <String> tags = new ArrayList<>();
		Map<String,Long> usage = new HashMap<>();
		for(Transaction expense: account.bankBook.debits){
			tags.addAll(expense._tags);
		}
		for(String tag : tags) usage.put(tag, (long) Collections.frequency(tags,tag));
		return usage;
	}

	public Transaction getExpensebyIndex(int index) { 
		return account.bankBook.debits.get(index); 
	}
	public Transaction getCreditbyIndex(int index) { 
		return account.bankBook.credits.get(index); 
	}
	public long getBankBookDebitEntries() {
		return (long) account.bankBook.debits.size();
	}
	public long getBankBookCreditEntries() {
		return (long) account.bankBook.credits.size();
	}
 
}
