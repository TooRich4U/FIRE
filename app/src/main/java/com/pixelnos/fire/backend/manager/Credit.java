package com.pixelnos.fire.backend.manager;

import java.util.Date;

public class Credit {
	
	public double _amount;
	public String _description;
	public Date _date;
	public String _type;

	public Credit(double amount, String description, Date date) {
		_amount = amount;
		_description = description;
		_date = date;
	}

}
