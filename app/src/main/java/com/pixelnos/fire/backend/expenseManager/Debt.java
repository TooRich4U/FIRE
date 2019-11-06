package com.pixelnos.fire.backend.expenseManager;

import java.util.Date;

public class Debt {
	public double amount;
	public double interest;
	public long period;
	public Date startDate;
	public String type;
	
	public Debt(double debtAmount, double debtInterest, long debtPeriod, Date debtStartDate) {
		 amount = debtAmount;
		 interest = debtInterest;
		 period = debtPeriod;
		 startDate = debtStartDate;
	}

}
