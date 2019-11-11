package com.pixelnos.fire.backend.UseCases;
import com.pixelnos.fire.backend.manager.Account;
import com.pixelnos.fire.backend.manager.Wallet;
import com.pixelnos.fire.backend.manager.Transaction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TransactionUseCase {
    @Test
    public void AddTransaction(){
        Wallet wallet = new Wallet();
        wallet.add(new Account("Savings","USD",6010));
        ArrayList<String> tags = new ArrayList<String>(Arrays.asList("Restaurant","Food", "Extra Expenses"));
        ArrayList<String> tags2 = new ArrayList<String>(Arrays.asList("Headset","Micro", "Extra Expenses"));
        wallet.accounts.get(0).addDebit(new Transaction(52,"Lausanne", new Date(), tags));
        wallet.accounts.get(0).addDebit(new Transaction(118,"Online", new Date(), tags2));
        assertEquals(wallet.accounts.get(0).data._balance, 6010-52-118,0.001);
    }
}
