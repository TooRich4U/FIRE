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

    public String toCSV() {
		String tags = "";
		for(String tag : this.tags){
			tags +=  tag + "|";
		}
        tags = tags.substring(0, tags.length() - 1);
		return String.format("%.2f," +
				"%s," +
				"%s," +
				"%s" , amount, location, date.toString(), tags);
    }
}
