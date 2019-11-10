package com.pixelnos.fire.backend.manager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserProfile {

    public ProfileData userData;
    public ArrayList<Wallet> wallets = new ArrayList<>();

    public void addWallet(Wallet wallet) {
        wallets.add(wallet);
    }
    public void removeWallet(Wallet wallet){
        wallets.remove(wallet);
    }
    public static class ProfileData{
        public int _age;
        public String _job;
        public double _salary;
        public double _savings;
        public double _passiveIncomeStart;
        public double _passiveIncomeTarget;
        public double _fireTarget;
        public String _currency;
    }

    public UserProfile(ProfileData newUserData) {
        userData = newUserData ;
    }

}
