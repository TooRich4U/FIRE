package com.pixelnos.fire.backend.manager;

import com.pixelnos.fire.ymlreader.YMLValue;

import java.util.ArrayList;
import java.util.Comparator;

public class Account {
    private String name;
    private AccountData data = new AccountData();
    private BankBook bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());

    public class AccountData {
        public Currency currency;
        public double balance;
        public double initialBalance;
    }

    public class BankBook {
        public ArrayList<Transaction> debits;
        public ArrayList<Transaction> credits;

        public BankBook(ArrayList<Transaction> debits, ArrayList<Transaction> credits) {
            this.debits = debits;
            this.credits = credits;
        }
    }

    public Account(YMLValue yml, CurrencyFactory factory){
        name = yml.get("name").asString();
        data.currency = factory.createFromYML(yml.get("currency"));
        data.balance = yml.get("balance").asDouble();
        data.initialBalance = yml.get("initial_balance").asDouble();
    }

    public Account(String accountName, Currency accountCurrency, double accountBalance) {
        name = accountName;
        data.currency = accountCurrency;
        data.balance = accountBalance;
        data.initialBalance = accountBalance;
    }

    public void addDebit(Transaction debit) {
        bankBook.debits.add(debit);
        updateAccountBalance(-debit.amount);
    }

    public void addDebits(ArrayList<Transaction> debits) {
        for (Transaction debit : debits) {
            bankBook.debits.add(debit);
            updateAccountBalance(-debit.amount);
        }
    }

    public void addCredit(Transaction credit) {
        bankBook.credits.add(credit);
        updateAccountBalance(credit.amount);
    }

    public void addCredits(ArrayList<Transaction> credits) {
        for (Transaction credit : credits) {
            bankBook.credits.add(credit);
            updateAccountBalance(credit.amount);
        }
    }

    public AccountData getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public BankBook getBankBook() {
        return bankBook;
    }

    private void updateAccountBalance(double amount) {
        data.balance += amount;
    }

    public void resetAccount() {
        bankBook = new BankBook(new ArrayList<Transaction>(), new ArrayList<Transaction>());
        data.balance = data.initialBalance;
    }

    public String toYML(String shift) {
        String yml = String.format(shift + "name: %s\n" +
                        shift + "balance: %.2f\n" +
                        shift + "initial_balance: %.2f\n" +
                        shift + "currency: %s\n"
                , name, data.balance, data.initialBalance, data.currency.toYML());
        return yml;
    }

    private String toCSV() {
        String csv = "";
        for (Transaction debit : bankBook.debits) {
            csv += "  - \n" + debit.toCSV() + "\n";
        }
        for (Transaction credit : bankBook.credits) {
            csv += "  - \n" + credit.toCSV() + "\n";
        }
        return csv;
    }
}

class TransactionSorter implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.date.compareTo(t2.date);
    }

}