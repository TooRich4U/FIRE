package com.pixelnos.fire.backend.expenseManager;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TransactionTest {
	@Test
	public void createExpense() {
		double newAmount = 345.8;
		String newLocation = "Tokyo";
		Date today = new Date();
		ArrayList <String> descriptions = new ArrayList<String>();
		descriptions.add("Something");
		descriptions.add("Food");
		Transaction transaction = new Transaction(newAmount, newLocation, today, descriptions);
		assertEquals(newAmount, transaction.amount,0.01);
		assertEquals(newLocation, transaction.location);
		assertEquals(today, transaction.date); 
		assertEquals(descriptions,transaction.tags); 
	}

}
