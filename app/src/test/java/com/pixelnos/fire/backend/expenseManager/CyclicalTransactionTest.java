package com.pixelnos.fire.backend.expenseManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class CyclicalTransactionTest {

	@Test
	public void createCyclicalTransactionTest() {
		Period period = Period.MONTHLY;
		Date startingDate = new Date();
		Transaction expenseTemplate = createExpenseTemplate();
		
		CyclicalTransaction cyclicalExpense = new CyclicalTransaction(expenseTemplate, period, startingDate);
		assertEquals(cyclicalExpense.getTransactionTemplate(), expenseTemplate);
		assertEquals(cyclicalExpense.getPeriod(), period);
		assertEquals(cyclicalExpense.getStartingDate(), startingDate);
		assertEquals(cyclicalExpense.lastPaymentDate(), startingDate);
	}
	
	@Test
	public void shouldPayBeforePeriod() {
		Period period = Period.MONTHLY;
		LocalDate date = LocalDate.now().minusDays(15);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date startingDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
		Transaction expenseTemplate = createExpenseTemplate();
		CyclicalTransaction cyclicalExpense = new CyclicalTransaction(expenseTemplate, period, startingDate);
		assertFalse(cyclicalExpense.isAfterPayDay(Calendar.getInstance().getTime()));
	}
	
	@Test
	public void createPaymentTest() {
		Period period = Period.MONTHLY;
		Date startingDate = new Date();
		Transaction expenseTemplate = createExpenseTemplate();
		LocalDate date = LocalDate.now().plusDays(30);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date nextPaymentDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());

		CyclicalTransaction cyclicalExpense = new CyclicalTransaction(expenseTemplate, period, startingDate);
		Transaction expense = cyclicalExpense.createPayment();
		assertEquals(expenseTemplate.amount, expense.amount,0.001);
		assertEquals(expenseTemplate.location, expense.location);
		assertEquals(expenseTemplate.tags, expense.tags);
		assertEquals(0, TimeUnit.DAYS.convert(cyclicalExpense.lastPaymentDate().getTime() - nextPaymentDate.getTime(), TimeUnit.MILLISECONDS));
		assertEquals(0, TimeUnit.DAYS.convert(expense.date.getTime() - nextPaymentDate.getTime(), TimeUnit.MILLISECONDS));
	}

	private Transaction createExpenseTemplate() {
		double newAmount = 345.8;
		String newLocation = "Tokyo";
		Date today = new Date();
		ArrayList <String> descriptions = new ArrayList<String>();
		descriptions.add("Fees");
		descriptions.add("Electric bill");
		return new Transaction(newAmount, newLocation, today, descriptions);
	}

}
