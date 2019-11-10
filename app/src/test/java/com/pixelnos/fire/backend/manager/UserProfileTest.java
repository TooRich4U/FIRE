package com.pixelnos.fire.backend.manager;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UserProfileTest {
    @Test
    public void createUser() {
        String job = "jobless";
        int age = 25;
        double netMonthlySalary = 250000;
        double savings = 8000;
        String currency = "EUR";
        UserProfile.ProfileData userData = new UserProfile.ProfileData();
        userData._age = age;
        userData._job = job;
        userData._salary = netMonthlySalary;
        userData._savings = savings;
        userData._currency = currency;
        userData._passiveIncomeTarget = 5000;
        userData._passiveIncomeStart = 45;
        userData._fireTarget = 0.50;
        UserProfile user = new UserProfile(userData);
        assertEquals(user.userData, userData);

    }
    @Test
    public void addWallet(){
        UserProfile.ProfileData userData = new UserProfile.ProfileData();
        UserProfile user = new UserProfile(userData);
        Wallet wallet = new Wallet();
        user.addWallet(wallet);
        ArrayList<Wallet> wallets = new ArrayList<>();
        wallets.add(wallet);
        assertEquals(user.wallets,wallets);
    }
    @Test
    public void removeWallet(){
        UserProfile.ProfileData userData = new UserProfile.ProfileData();
        UserProfile user = new UserProfile(userData);
        Wallet wallet1 = new Wallet();
        Wallet wallet2 = new Wallet();
        user.addWallet(wallet1);
        user.addWallet(wallet2);
        user.removeWallet(wallet1);
        ArrayList<Wallet> wallets = new ArrayList<>();
        wallets.add(wallet1);
        wallets.add(wallet2);
        wallets.remove(wallet1);
        assertEquals(user.wallets,wallets);
    }
    @Test
    // For my understanding. Do not delete.
    public void createFullAUserAccount(){
        UserProfile.ProfileData profileData = new UserProfile.ProfileData();
        UserProfile user = new UserProfile(profileData);
        Wallet wallet = new Wallet();
        Account account = new Account("New Account1","EUR",10000);
        Transaction transaction = new Transaction(1000,"Mars",new Date(),new ArrayList<String>());
        account.addDebit(transaction);
        wallet.add(account);
        user.addWallet(wallet);
    }
}
