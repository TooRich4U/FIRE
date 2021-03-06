package com.pixelnos.fire.backend.manager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

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
		assertEquals(newAmount, transaction.amount,0.01);
		assertEquals(newLocation, transaction.location);
		assertEquals(today, transaction.date);
		assertEquals(descriptions,transaction.tags);
	}

	@Test
	public void toCSV(){
		double newAmount = 345.8;
		String newLocation = "Tokyo";
		Date today = new Date();
		ArrayList <String> descriptions = new ArrayList<>();
		descriptions.add("Something");
		descriptions.add("Food");
		Transaction transaction = new Transaction(newAmount, newLocation, today, descriptions);
		assertEquals(
				"345.80,Tokyo," + today.toString() + "," +
						descriptions.get(0)+"|" + descriptions.get(1),
				transaction.toCSV());
	}

}
