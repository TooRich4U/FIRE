package com.pixelnos.fire.backend.manager;

import static org.junit.Assert.assertEquals; 

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class ReportTests {
	private String accountNameAtTest = "MyFirstAccount";
	private Currency accountCurrencyAtTest = new Currency("Euros", "EUR", "€", 1.10);
	private double accountBalanceAtTest = 1000;
	private Account account = new Account(accountNameAtTest, accountCurrencyAtTest, accountBalanceAtTest);
	private ArrayList <Transaction> createDummyTransactions(int numberOfEntries){
		ArrayList <String> description = new ArrayList<>();
		ArrayList <Transaction> transactions = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for(int i = 0 ; i < numberOfEntries+1; i ++) {
			 description.add("Utilities"); 
			 description.add("Toys"); 
			 description.add("Beverages"); 
			 description.add("Restaurants");
			 calendar.set(2018,1,1+i,1,1,1);
			 transactions.add(new Transaction(10,"Paris",calendar.getTime(),description));
		}
		return transactions;
		
	}

	@Test
	public void reportDebitTransactionByDateType() {
		ArrayList <Transaction> transactions = new ArrayList<>();
		ArrayList <Transaction> mondayTransactions = new ArrayList<>();
		Calendar calendar = Calendar.getInstance(); 
		for(int i=0; i <30;i++) {
			calendar.set(2018, 11, 1+i, 59, 59, 59);
			Date date = calendar.getTime();
			Transaction transaction = new Transaction(10, null, date, null);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				mondayTransactions.add(transaction);
			}
			transactions.add(transaction);
			account.addDebit(transaction);
		}   
		Reporter report = new Reporter(account);
		assertEquals(mondayTransactions,report.getDebitTransactionByWeekDay(Calendar.MONDAY));
		account.resetAccount();
	}

	@Test
	public void reportDebitTransactionByDate() {
		ArrayList <Transaction> transactions = new ArrayList<>();
		double amount = 10;
		Calendar calendar = Calendar.getInstance();
		for(int i=0; i <30;i++) {
			calendar.set(2018, 11, 1+i, 59, 59, 59);
			Date date = calendar.getTime();
			Transaction transaction = new Transaction(amount, null, date, null);
			if (i > 28) transactions.add(transaction);
			account.addDebit(transaction);
		}   
		Reporter report = new Reporter(account);
		calendar.set(2018,11,29,59,59,59) ;
		assertEquals(transactions,report.getTransactionFromDay(calendar));
		account.resetAccount();
	}
	@Test
	public void getExpenseByDescription(){
		ArrayList <String> tags = new ArrayList<>();
		tags.add("Something");
		tags.add("Food");
		Transaction expense = new Transaction(100, "", new Date(), tags);
		ArrayList<String> tags2 = new ArrayList<>(tags);
		tags2.remove(0);
		Transaction expense2 = new Transaction(100, "", new Date(), tags2);
		account.addDebit(expense);
		account.addDebit(expense2);
		ArrayList <Transaction> expensesByDescription = new ArrayList<>();
		expensesByDescription.add(expense); 
		Reporter report = new Reporter(account);
		assertEquals(expensesByDescription, report.getExpensesByDescription("Something"));
		account.resetAccount();
	}
	@Test
	public void getCurrentRecordedTags() {
		ArrayList <String> descriptions = new ArrayList<>();
		descriptions.add("Something");
		descriptions.add("Food");
		Transaction expense = new Transaction(100, "", new Date(), descriptions); 
		account.addDebit(expense);
		account.addDebit(expense); 
		Reporter report = new Reporter(account);
		assertEquals(descriptions,report.getCurrentExpensesTags()); 
		account.resetAccount();
	}
	@Test
	public void getTagsUsage() {
		ArrayList <String> descriptions1 = new ArrayList<>();
		descriptions1.add("Something");
		descriptions1.add("Food");
		Map<String,Long> occurrences = new HashMap<>();
		occurrences.put("Something", (long) 3);
		occurrences.put("Food", (long) 3);
		account.addDebit(new Transaction(100, "", new Date(), descriptions1)); 
		account.addDebit(new Transaction(100, "", new Date(), descriptions1));
		account.addDebit(new Transaction(100, "", new Date(), descriptions1));
		Reporter report = new Reporter(account);
		assertEquals(occurrences,report.getTagsUsageCount());
		account.resetAccount();
	}
	@Test 
	public void getExpenseByIndex() {
		account.addDebit(new Transaction(101, "", null, null)); 
		account.addDebit(new Transaction(102, "", null, null));  
		account.addDebit(new Transaction(103, "", null, null)); 
		Reporter report = new Reporter(account);
		assertEquals(101,report.getExpenseByIndex(0).amount,0.01);
		account.resetAccount();
	}
	@Test 
	public void getCreditByIndex() {
		account.addCredit(new Transaction(101, "", null, null)); 
		account.addCredit(new Transaction(102, "", null, null));  
		account.addCredit(new Transaction(103, "", null, null));
		
		Reporter report = new Reporter(account);
		assertEquals(101,report.getCreditByIndex(0).amount,0.01);
		account.resetAccount();
	}

	@Test
	public void getBankBookEntries() {
		ArrayList <Transaction> transactions = createDummyTransactions(40);
		Reporter report = new Reporter(account);
		for(Transaction transaction : transactions) {
			account.addDebit(transaction);
		}
		assertEquals(41,report.getBankBookDebitEntries());
		for(Transaction transaction : transactions) {
			account.addCredit(transaction);
		}
		assertEquals(41,report.getBankBookCreditEntries());
	}

	
}
