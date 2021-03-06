package com.pixelnos.fire.backend.manager;

import com.pixelnos.fire.ymlreader.YMLReader;
import com.pixelnos.fire.ymlreader.YMLValue;
import com.pixelnos.fire.ymlreader.YMLWriter;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AccountTest {
	
	private String accountNameAtTest = "MyFirstAccount";
	private Currency accountCurrencyAtTest = new Currency("Euros", "EUR", "€", 1.10);
	private double accountBalanceAtTest = 1000;
	private Account account = new Account(accountNameAtTest,accountCurrencyAtTest,accountBalanceAtTest);
	
	@Test
	public void createAccount() {
		assertEquals(accountNameAtTest, account.getName());
		assertEquals(accountCurrencyAtTest,account.getData().currency);
		assertEquals(Math.abs(accountBalanceAtTest),account.getData().balance,0.001);
		assertEquals(Math.abs(accountBalanceAtTest),account.getData().balance,0.001);
	}

	@Test
	public void createAccountFromYML() {
	    String yml = "name: Savings\n"+
                "currency: USD\n"+
                "balance: 548.45\n"+
                "initial_balance: 89.41";
        YMLValue value = YMLReader.read(yml);
        CurrencyFactory factory = Mockito.mock(CurrencyFactory.class);
        Currency currency = Mockito.mock(Currency.class);
        Mockito.when(factory.createFromYML(Mockito.any(YMLValue.class))).thenReturn(currency);
		Account account = new Account(value, factory);
		assertEquals("Savings", account.getName());
		assertEquals(currency,account.getData().currency);
		assertEquals(548.45,account.getData().balance,0.001);
		assertEquals(89.41,account.getData().initialBalance,0.001);
	}

	@Test
	public void addExpense() {
		String newLocation = "Paris";
		Date newDate = new Date(); 
		double expenseAmount = 300; 
		ArrayList <String> description = new ArrayList<>();
		description.add("Utilities");
		description.add("Food");
		Transaction expense = new Transaction(expenseAmount, newLocation, newDate, description);
		account.addDebit(expense);
		assertEquals(expense, account.getBankBook().debits.get(0));
		assertEquals(accountBalanceAtTest-expenseAmount,account.getData().balance,0.001);
		assertEquals(accountBalanceAtTest,account.getData().initialBalance,0.001);
	}
	@Test
	public void resetAccount() {
		ArrayList <String> description = new ArrayList<>();
		description.add("Utilities");
		description.add("Food");
		Transaction expense = new Transaction(100, "", null, null);
		account.addDebit(expense);
		account.addDebit(expense);
		account.addDebit(expense);
		account.resetAccount();
		assertEquals(accountBalanceAtTest,account.getData().balance,0.01);
	}
	 
	
	@Test
	public void addBalance() {
		ArrayList <String> descriptions = new ArrayList<>();
		descriptions.add("Something");
		double creditedBalance = 100;
		Transaction credit = new Transaction(100,"Place", new Date(),descriptions);
		account.addCredit(credit);
		assertEquals(accountBalanceAtTest+creditedBalance,account.getData().balance,0.001);
		assertEquals(account.getData().balance -creditedBalance, account.getData().initialBalance,0.001);
	}
	
	@Test
	public void getCurrentBalance()
	{
		ArrayList <String> descriptions = new ArrayList<>();
		descriptions.add("Something");
		descriptions.add("Food"); 
		account.addDebit(new Transaction(100, "", new Date(), descriptions)); 
		account.addDebit(new Transaction(100, "", new Date(), descriptions)); 
		assertEquals(accountBalanceAtTest - (100 *2), account.getData().balance,0.001);
	}
	private ArrayList <Transaction> createDummyTransactions(int numberOfEntries){
		ArrayList <String> description = new ArrayList<>();
		ArrayList <Transaction> transactions = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for(int i = 0 ; i < numberOfEntries+1; i ++) {
			description.add("Utilities");
			description.add("Toys");
			description.add("Beverages");
			description.add("Restaurants");
			int year = (int) (Math.random() * (2999)) + 1;
			int amount =  (int) (Math.random() * (1000));
			calendar.set(year,1,1+i,1,1,1);
			transactions.add(new Transaction(amount,"Paris",calendar.getTime(),description));
		}
		return transactions;

	}
	@Test
	public void addDebits(){
		ArrayList<Transaction> transactions = createDummyTransactions(10);
		account.addDebits(transactions);
		assertEquals(transactions, account.getBankBook().debits);
	}
	@Test
	public void addCredits(){
		ArrayList<Transaction> transactions = createDummyTransactions(10);
		account.addCredits(transactions);
		assertEquals(transactions, account.getBankBook().credits);
	}

	@Test
	public void toYML(){
		Currency currency = Mockito.mock(Currency.class);
		Account newAccount = new Account(accountNameAtTest,currency,1000.0);
		Mockito.when(currency.toYML()).thenReturn(new YMLValue("Eur"));
		assertEquals(
				"name: MyFirstAccount\n" +
						"balance: 1000.00\n" +
						"initial_balance: 1000.00\n" +
						"currency: Eur", YMLWriter.write(newAccount.toYML()));
	}

	@Test
	public void sortTransactions(){
		ArrayList<Transaction> transactions = createDummyTransactions(10);
		transactions.sort(new TransactionSorter());
		account.addDebits(transactions);
		account.getBankBook().debits.sort(new TransactionSorter());
		assertEquals(transactions,account.getBankBook().debits);
	}

	class TransactionSorter implements Comparator<Transaction> {
		@Override
		public int compare(Transaction t1, Transaction t2) {
			return t1.date.compareTo(t2.date);
		}

	}


}

