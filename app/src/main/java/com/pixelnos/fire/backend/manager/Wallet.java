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

}
