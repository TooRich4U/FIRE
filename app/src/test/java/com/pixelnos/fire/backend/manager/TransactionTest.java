package com.pixelnos.fire.backend.manager;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class TransactionTest {
	@Test
	public void createExpense() {
		double newAmount = 345.8;
		String newLocation = "Tokyo";
		Date today = new Date();
		ArrayList <String> descriptions = new ArrayList<>();
		descriptions.add("Something");
		descriptions.add("Food");
		Transaction transaction = new Transaction(newAmount, newLocation, today, descriptions);
		assertEquals(newAmount, transaction._amount,0.01);
		assertEquals(newLocation, transaction._location);
		assertEquals(today, transaction._date);
		assertEquals(descriptions,transaction._tags);
	}

	@Test
	public void toYML(){
		double newAmount = 345.8;
		String newLocation = "Tokyo";
		Date today = new Date();
		ArrayList <String> descriptions = new ArrayList<>();
		descriptions.add("Something");
		descriptions.add("Food");
		Transaction transaction = new Transaction(newAmount, newLocation, today, descriptions);
		assertEquals(
				"amount: 345.80\n" +
						"location: Tokyo\n" +
						"date: " + today.toString() + "\n" +
						"tags:\n"+"  - " + descriptions.get(0)+"\n  - " + descriptions.get(1) +"\n", transaction.toYML());
	}

}
