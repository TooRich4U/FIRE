package com.pixelnos.fire.backend.manager;

public class UserProfile {

    private ProfileData userData;
    private Wallet wallet;

    public static class ProfileData{
        public int age;
        public String job;
        public double salary;
        public double savings;
        public double passiveIncomeStart;
        public double passiveIncomeTarget;
        public double fireTarget;
        public String currency;
    }

    public UserProfile(ProfileData newUserData, Wallet wallet) {
        userData = newUserData ;
        this.wallet = wallet;
    }

    public ProfileData getUserData(){
        return userData;
    }
    public Wallet getWallet(){
        return wallet;
    }

}
