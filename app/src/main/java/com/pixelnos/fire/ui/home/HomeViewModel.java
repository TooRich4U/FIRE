package com.pixelnos.fire.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixelnos.fire.MainActivity;
import com.pixelnos.fire.backend.manager.Account;
import com.pixelnos.fire.backend.manager.UserProfile;
import com.pixelnos.fire.backend.manager.Wallet;

import java.util.Map;

public class HomeViewModel extends ViewModel {
    UserProfile user = MainActivity.user;
    String currencySymbol = user.getWallet().getAccountByName("Account 1").getData().currency.getSymbol();
    Double accountTotal = getAccountsTotal(user.getWallet());
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Account Total : " + accountTotal.toString() + currencySymbol) ;


    }

    private double getAccountsTotal(Wallet wallet){
        double accountSum = 0;
        Map<String, Account> accounts = MainActivity.user.getWallet().getAccounts();
        int i = 0;
        for (Account account : accounts.values())  accountSum += account.getData().balance;
        return  accountSum;
    }
    public LiveData<String> getText() {
        return mText;
    }
}