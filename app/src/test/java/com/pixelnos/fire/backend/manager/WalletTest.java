package com.pixelnos.fire.backend.manager;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

    @Test
    public void toYML(){
        Wallet wallet = new Wallet();
        Account account = Mockito.mock(Account.class);
        Account account2 = Mockito.mock(Account.class);
        Mockito.when(account.getName()).thenReturn("account1");
        Mockito.when(account2.getName()).thenReturn("account2");
        wallet.add(account);
        wallet.add(account2);
        Mockito.when(account.toYML(Mockito.anyString())).thenReturn("      account1");
        Mockito.when(account2.toYML(Mockito.anyString())).thenReturn("      account2");
        assertEquals(
                "Wallet:\n" +
                "  accounts:\n" +
                "    -\n" +
                "      account2\n" +
                "    -\n" +
                "      account1\n", wallet.toYML(""));
    }

    @Test
    public void createWalletFromYML(){
        String yml = "Wallet:\n" +
                "  accounts:\n" +
                "    -\n" +
                "       account1\n"+
                "    -\n" +
                "       account2\n";

        try{
            AccountFactory factory = Mockito.mock(AccountFactory.class);
            Account account = Mockito.mock(Account.class);
            Account account2 = Mockito.mock(Account.class);
            Mockito.when(factory.createAccountFromYML(Mockito.anyString())).thenReturn(account).thenReturn(account2);
            Mockito.when(account.getName()).thenReturn("account1");
            Mockito.when(account2.getName()).thenReturn("account2");
            Wallet wallet = new Wallet(factory, yml);
            System.out.print(wallet.getAccounts().keySet());
            assertEquals(2, wallet.getAccounts().size());
        }
        catch(YMLInvalidException e){
            fail("Wallet creation failed.");
        }
    }

}
