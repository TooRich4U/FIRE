package com.pixelnos.fire.backend.manager;

import java.util.Date;

public class Debt {
	public double _amount;
	public double _interest;
	public long _period;
	public Date _startDate;
	public String _type;
	
	public Debt(double amount, double interest, long period, Date startDate) {
		 _amount = amount;
		 _interest = interest;
		 _period = period;
		 _startDate = startDate;
	}

}
