package com.pixelnos.fire.backend.manager;

import java.util.Date;

public class Credit {
	
	public double amount;
	public String description;
	public Date date;
	public String type;

	public Credit(double amount, String description, Date date) {
		this.amount = amount;
		this.description = description;
		this.date = date;
	}

}
