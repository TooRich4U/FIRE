package com.pixelnos.fire.backend.manager;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {

	public double _amount;
	public String _location;
	public Date _date;
	public ArrayList<String> _tags;
	public String _type;
	
	
	public Transaction(double amount, String location, Date date, ArrayList<String> tags) {
		_amount = amount;
		_location = location;
		_date = date;
		_tags = tags;
	}

	public Transaction(Transaction newTransaction) { 
		_amount = newTransaction._amount;
		_location = newTransaction._location;
		_date = newTransaction._date;
		_tags = newTransaction._tags;
		_type = newTransaction. _type;
	}
}
