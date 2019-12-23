package com.pixelnos.fire.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixelnos.fire.MainActivity;
import com.pixelnos.fire.backend.manager.UserProfile;

public class HomeViewModel extends ViewModel {
    UserProfile user = MainActivity.user;
    String currencySymbol = user.getWallet().getAccountByName("New Account1").getData().currency.getSymbol();
    Double accountTotal = user.getWallet().getAccountByName("New Account1").getData().balance;
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Account Total : " + accountTotal.toString() + currencySymbol) ;


    }

    public LiveData<String> getText() {
        return mText;
    }
}