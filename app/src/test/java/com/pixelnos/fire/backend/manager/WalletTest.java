package com.pixelnos.fire.backend.manager;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WalletTest {

    @Test
    public void createWallet() {
        Wallet wallet = new Wallet();
        ArrayList<Account> walletList = new ArrayList<>();
        Account account1 = new Account("Something", "EUR ", 10000);
        wallet.add(account1);
        walletList.add(account1);
        assertEquals(wallet.accounts, walletList);
    }

    @Test
    public void addAndRemoveAccount() {
        Wallet wallet = new Wallet();
        ArrayList<Account> walletList = new ArrayList<>();
        Account account1 = new Account("Something", "EUR ", 10000);
        Account account2 = new Account("Something", "EUR ", 10000);
        wallet.add(account1);
        wallet.add(account2);
        walletList.add(account1);
        walletList.add(account2);
        assertEquals(wallet.accounts, walletList);
        wallet.delete(account2);
        walletList.remove(account2);
        assertEquals(wallet.accounts, walletList);
    }


    @Test
    public void sumOfWallet() {
        Wallet wallet = new Wallet();
        Account account1 = new Account("Something", "EUR", 10000);
        wallet.add(account1);
        assertEquals(wallet.balanceOf(account1), account1.data._balance,0.01);
    }

}
