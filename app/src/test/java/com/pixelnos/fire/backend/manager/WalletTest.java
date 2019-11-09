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
    public void addAndRemoveAccount(){
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
        ArrayList<Account> walletList = new ArrayList<>();
        Account account1 = new Account("Something", "EUR", 10000);
        Account account2 = new Account("Something", "EUR", 10000);
        Account account3 = new Account("Something", "EUR", 10000);
        wallet.add(account1);
        wallet.add(account2);
        wallet.add(account3);
        walletList.add(account1);
        walletList.add(account2);
        walletList.add(account3);
        assertEquals(wallet.balanceOf("Something","EUR"), 10000,0.01);

    }

    public void checkWalletDuplicates(){
        Wallet wallet = new Wallet();
        ArrayList<Account> walletList = new ArrayList<>();
        Account account1 = new Account("Something", "EUR ", 10000);
        Account account2 = new Account("Something", "EUR ", 10000);
        Account account3 = new Account("Something2", "EUR ", 10000);

    }
}
