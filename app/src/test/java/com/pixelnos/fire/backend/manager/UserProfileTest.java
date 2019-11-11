package com.pixelnos.fire.backend.manager;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UserProfileTest {
    @Test
    public void createUser() {
        UserProfile.ProfileData userData = Mockito.mock(UserProfile.ProfileData.class);
        Wallet wallet = new Wallet();
        UserProfile user = new UserProfile(userData, wallet);
        assertEquals(userData, user.getUserData());
    }

    @Test
    // For my understanding. Do not delete.
    public void createFullAUserAccount(){
        UserProfile.ProfileData profileData = new UserProfile.ProfileData();
        Wallet wallet = new Wallet();
        UserProfile user = new UserProfile(profileData, wallet);

        Currency currency = new Currency("Euros", "EUR", "€", 1.10);
        Account account = new Account("New Account1", currency,10000);
        Transaction transaction = new Transaction(1000,"Mars",new Date(),new ArrayList<String>());
        account.addDebit(transaction);
        wallet.add(account);
    }
}
