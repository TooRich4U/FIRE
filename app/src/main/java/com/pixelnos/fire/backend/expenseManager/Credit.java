package com.pixelnos.fire.backend.expenseManager;

import java.util.Date;

public class Credit {
	
	public double amount;
	public String description;
	public Date date;
	public String type;
	public Credit(double newAmount, String newDescription, Date newDate) {
		amount = newAmount;
		description = newDescription;
		date = newDate;;
	}

}
