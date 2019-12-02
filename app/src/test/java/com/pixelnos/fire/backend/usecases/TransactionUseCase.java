package com.pixelnos.fire.backend.usecases;
import com.pixelnos.fire.backend.manager.Account;
import com.pixelnos.fire.backend.manager.AccountFactory;
import com.pixelnos.fire.backend.manager.Currency;
import com.pixelnos.fire.backend.manager.Transaction;
import com.pixelnos.fire.backend.manager.Wallet;
import com.pixelnos.fire.ymlreader.YMLWriter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionUseCase {
    @Test
    public void addTransaction(){
        Wallet wallet = new Wallet();
        wallet.add(new Account("Savings",
                new Currency("US Dollars","USD", "$", 1.0),
                6010));
        ArrayList<String> tags = new ArrayList<String>(Arrays.asList("Restaurant","Food", "Extra Expenses"));
        ArrayList<String> tags2 = new ArrayList<String>(Arrays.asList("Headset","Micro", "Extra Expenses"));

        Account savingAccount = wallet.getAccountByName("Savings");
        savingAccount.addDebit(new Transaction(52,"Lausanne", new Date(), tags));
        savingAccount.addDebit(new Transaction(118,"Online", new Date(), tags2));
        assertEquals(savingAccount.getData().balance, 6010-52-118,0.001);
    }

    @Test
    public void SaveLoadWallet(){
        Wallet wallet = new Wallet();
        wallet.add(new Account("Savings",
                new Currency("US Dollars","USD", "$", 1.0),
                6010));
        wallet.add(new Account("Cash",
                new Currency("Euros","EUR", "€", 0.91),
                658.21));

        String walletInYML = "";
        try{
            walletInYML = YMLWriter.write(wallet.toYML());
        }catch(Exception e){
            fail("Exception raised while writing");
        }

        Wallet savedWallet = new Wallet(new AccountFactory(), walletInYML);
        String walletInYML2 = "";
        try{
            walletInYML2 = YMLWriter.write(savedWallet.toYML());
        }catch(Exception e){
            fail("Exception raised while writing");
        }
        assertEquals(walletInYML, walletInYML2);
        assertTrue(!walletInYML.isEmpty());
        assertTrue(!walletInYML2.isEmpty());
    }
}
