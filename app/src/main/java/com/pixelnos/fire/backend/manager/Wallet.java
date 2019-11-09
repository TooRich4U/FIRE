package com.pixelnos.fire.backend.manager;

import java.util.ArrayList;

public class Wallet {
    public ArrayList<Account> accounts = new ArrayList<>();

    public void add(Account account) {
        accounts.add(account);
    }

    public void delete(Account account){
        accounts.remove(account);
    }
    public double balanceOf(String accountName, String currency){
        for(Account account : accounts){
            if(account.data._name.equals(accountName) & account.data._currency.equals(currency))
                return account.data._balance;
        }
        return(0);
    }
}
