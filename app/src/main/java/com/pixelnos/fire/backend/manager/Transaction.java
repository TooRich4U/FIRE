package com.pixelnos.fire.backend.manager;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {

	public double amount;
	public String location;
	public Date date;
	public ArrayList<String> tags;
	public String type;
	
	
	public Transaction(double amount, String location, Date date, ArrayList<String> tags) {
		this.amount = amount;
		this.location = location;
		this.date = date;
		this.tags = tags;
	}

	public Transaction(Transaction newTransaction) { 
		amount = newTransaction.amount;
		location = newTransaction.location;
		date = newTransaction.date;
		tags = newTransaction.tags;
		type = newTransaction.type;
	}

    public String toYML(String shift) {
		String tags = "";
		for(String tag : this.tags){
			tags += shift + "  - " + tag + "\n";
		}
		return String.format(shift + "amount: %.2f\n" +
				shift + "location: %s\n" +
				shift + "date: %s\n" +
				shift + "tags:\n%s" , amount, location, date.toString(), tags);
    }
}
