package com.pixelnos.fire.backend.expenseManager;

import static org.junit.Assert.assertEquals; 

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class ReportTests {
	public String accountNameAtTest = "MyFirstAccount";
	public String accountCurrencyAtTest = "EUR";
	public double accountBalanceAtTest = 1000;
	Account account = new Account(accountNameAtTest,accountCurrencyAtTest,accountBalanceAtTest);
	private ArrayList <Transaction> createDummyTransactions(int numberOfEntries){
		ArrayList <String> description = new ArrayList<String>();
		ArrayList <Transaction> transactions = new ArrayList<Transaction>();
		Calendar calendar = Calendar.getInstance();
		
		for(int i = 0 ; i < numberOfEntries+1; i ++) {
			 description = new ArrayList<String>();
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
	public void reportTransactionByDateType_Debit() {
		ArrayList <String> description = new ArrayList<String>();
		ArrayList <Transaction> transactions = new ArrayList<Transaction>(); 
		ArrayList <Transaction> mondayTransactions = new ArrayList<Transaction>();
		description.add("Utilities");
		description.add("Food");
		double amount = 10;
		String location = null;
		Calendar calendar = Calendar.getInstance(); 
		for(int i=0; i <30;i++) {
			calendar.set(2018, 11, 1+i, 59, 59, 59);
			Date date = calendar.getTime();
			Transaction transaction = new Transaction(amount, location, date, description);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) mondayTransactions.add(transaction);
			transactions.add(transaction);
			account.addDebit(transaction);
		}   
		Reporter report = new Reporter(account);
		assertEquals(mondayTransactions,report.getDebitTransactionByWeekDay(Calendar.MONDAY));
		account.resetAccount();
	}
	@Test
	public void reportTransactionByDate_Debit() {
		ArrayList <String> description = new ArrayList<String>();
		ArrayList <Transaction> transactions = new ArrayList<Transaction>(); 
		description.add("Utilities");
		description.add("Food");
		double amount = 10;
		String location = null;
		Calendar calendar = Calendar.getInstance(); 
		for(int i=0; i <30;i++) {
			calendar.set(2018, 11, 1+i, 59, 59, 59);
			Date date = calendar.getTime();
			Transaction transaction = new Transaction(amount, location, date, description);
			if (i > 28) transactions.add(transaction);
			account.addDebit(transaction);
		}   
		Reporter report = new Reporter(account);
		calendar.set(2018,11,29,59,59,59) ;
		assertEquals(transactions,report.getTransactionFromDay(calendar));
		account.resetAccount();
	}
	public void getExpenseBydescription(){
		ArrayList <String> descriptions = new ArrayList<String>();
		descriptions.add("Something");
		descriptions.add("Food");
		Transaction expense = new Transaction(100, "", new Date(), descriptions); 
		account.addDebit(expense);   
		ArrayList <Transaction> expensesByDescription = new ArrayList<Transaction>();
		expensesByDescription.add(expense); 
		Reporter report = new Reporter(account);
		assertEquals(expensesByDescription, report.getExpensesByDescription("Something"));
		account.resetAccount();
	}
	@Test
	public void getCurrentRecordedTags() {
		ArrayList <String> descriptions = new ArrayList<String>();
		descriptions.add("Something");
		descriptions.add("Food");
		Transaction expense = new Transaction(100, "", new Date(), descriptions); 
		account.addDebit(expense);
		account.addDebit(expense); 
		Reporter report = new Reporter(account);
		assertEquals(descriptions,report.getCurrentExpensesTags()); 
		account.resetAccount();
	}
	public void getTagsUsage() {
		ArrayList <String> descriptions1 = new ArrayList<String>();
		descriptions1.add("Something");
		descriptions1.add("Food"); 
		ArrayList <String> descriptions2 = new ArrayList<String>();
		descriptions2.add("Something");
		descriptions2.add("Food"); 
		ArrayList <String> descriptions3 = new ArrayList<String>();
		descriptions3.add("Something");
		descriptions3.add("Food"); 
		Map<String,Long> occurrencies = new HashMap<>();
		occurrencies.put("Something", (long) 3);
		occurrencies.put("Food", (long) 3); 
		account.addDebit(new Transaction(100, "", new Date(), descriptions1)); 
		account.addDebit(new Transaction(100, "", new Date(), descriptions2));  
		account.addDebit(new Transaction(100, "", new Date(), descriptions3));
		Reporter report = new Reporter(account);
		assertEquals(occurrencies,report.getTagsUsageCount());  
		account.resetAccount();
	}
	@Test 
	public void getExpenseByIndex() {
		account.addDebit(new Transaction(101, "", null, null)); 
		account.addDebit(new Transaction(102, "", null, null));  
		account.addDebit(new Transaction(103, "", null, null)); 
		Reporter report = new Reporter(account);
		assertEquals(101,report.getExpensebyIndex(0).amount,0.01);
		account.resetAccount();
	}
	@Test 
	public void getCreditByIndex() {
		account.addCredit(new Transaction(101, "", null, null)); 
		account.addCredit(new Transaction(102, "", null, null));  
		account.addCredit(new Transaction(103, "", null, null));
		
		Reporter report = new Reporter(account);
		assertEquals(101,report.getCreditbyIndex(0).amount,0.01);
		account.resetAccount();
	}
	@Test
	public void getBankBookEntries() {
		ArrayList <Transaction> transactions = createDummyTransactions(40);
		Reporter report = new Reporter(account);
		for(Transaction transaction : transactions) account.addDebit(transaction);
		assertEquals(41,report.getBankBookDebitEntries());
		for(Transaction transaction : transactions) account.addCredit(transaction);
		assertEquals(41,report.getBankBookCreditEntries());
	}
	
}
