package com.pixelnos.fire.backend.manager;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class WalletTest {

    @Test
    public void createWallet() {
        Wallet wallet = new Wallet();
        Account account = Mockito.mock(Account.class);
        Mockito.when(account.getName()).thenReturn("Something");
        wallet.add(account);

        assertEquals(account, wallet.getAccountByName("Something"));
    }

    @Test
    public void addAndRemoveAccount() {
        Wallet wallet = new Wallet();
        Account account1 = Mockito.mock(Account.class);
        Account account2 = Mockito.mock(Account.class);
        Mockito.when(account1.getName()).thenReturn("Something1");
        Mockito.when(account2.getName()).thenReturn("Something2");
        wallet.add(account1);
        wallet.add(account2);
        assertEquals(2, wallet.getAccounts().size());

        wallet.delete(account2);
        assertEquals(1, wallet.getAccounts().size());
        assertEquals(account1, wallet.getAccountByName("Something1"));
    }




}
