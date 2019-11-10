package com.pixelnos.fire.backend.manager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WalletTest {

    @Test
    public void createWallet() {
        Wallet wallet = new Wallet();
        ArrayList<Account> walletList = new ArrayList<>();
        Account account1 = new Account("Something", "EUR ", 10000);
        wallet.add(account1);
        walletList.add(account1);
        assertEquals(wallet.getAccountByName("Something"), account1);
    }

    @Test
    public void addAndRemoveAccount() {
        Wallet wallet = new Wallet();
        Map<String,Account> walletList = new HashMap<>();;
        Account account1 = new Account("Something1", "EUR ", 10000);
        Account account2 = new Account("Something2", "EUR ", 10000);
        wallet.add(account1);
        wallet.add(account2);
        walletList.put(account1.data._name,account1);
        walletList.put(account2.data._name,account2);
        assertEquals(wallet.getAccounts(), walletList);

        wallet.delete(account2);
        walletList.remove(account2.data._name);
        assertEquals(wallet.getAccounts(), walletList);
    }




}
