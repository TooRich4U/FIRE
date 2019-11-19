package com.pixelnos.fire.backend.manager;

import java.util.Date;

public class Debt {
	public double amount;
	public double interest;
	public long period;
	public Date startDate;

	public Debt(double amount, double interest, long period, Date startDate) {
		 this.amount = amount;
		 this.interest = interest;
		 this.period = period;
		 this.startDate = startDate;
	}

}
