package com.pixelnos.fire.backend.expenseManager;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {

	public double amount;
	public String location;
	public Date date;
	public ArrayList<String> tags; 
	public String type;
	
	
	public Transaction(double newAmount, String newLocation, Date newDate, ArrayList<String> newTags) {
		amount = newAmount;
		location = newLocation;
		date = newDate; 
		tags = newTags;
	}

	public Transaction(Transaction newTransaction) { 
		amount = newTransaction.amount;
		location = newTransaction.location;
		date = newTransaction.date; 
		tags = newTransaction.tags;
	}
}
