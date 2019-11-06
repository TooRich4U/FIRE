package com.pixelnos.fire.backend.expenseManager;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CyclicalTransaction {

	private Transaction expenseTemplate;
	private Period period;
	private Date startingDate;
	private Date lastPaymentDate;

	public CyclicalTransaction(Transaction newExpense, Period newPeriod, Date newStartingDate) {
		expenseTemplate = newExpense;
		period = newPeriod;
		startingDate = newStartingDate;
		lastPaymentDate = startingDate;
	}

	public Transaction getTransactionTemplate() {
		return expenseTemplate;
	}

	public Period getPeriod() {
		return period;
	}

	public Date getStartingDate() {
		return startingDate;
	}
	
	public Date lastPaymentDate() {
		return lastPaymentDate;
	}
	
	public boolean isAfterPayDay(Date date) {
		long diffInMilliseconds = date.getTime() - lastPaymentDate.getTime();
		long diff = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
		return diff >= Period.getPeriodInDays(period);
	}

	public Transaction createPayment() {
		Transaction newExpense = new Transaction(expenseTemplate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(lastPaymentDate);
		calendar.add(Calendar.DAY_OF_MONTH, (int) Period.getPeriodInDays(period));
		newExpense.date = calendar.getTime();
		lastPaymentDate = newExpense.date;
		return newExpense;
	}

 

}
