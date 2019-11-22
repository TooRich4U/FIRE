package com.pixelnos.fire.backend.manager;

import java.util.HashMap;
import java.util.Map;

public class Wallet {
    private Map<String,Account> accounts = new HashMap<>();

    public Account getAccountByName(String accountName){
        return accounts.get(accountName);
    }
    public Map<String,Account> getAccounts (){
        return accounts;
    }
    public void add(Account account) {
        accounts.put(account.getName(),account);
    }

    public void delete(Account account){
        accounts.remove(account.getName());
    }

}
