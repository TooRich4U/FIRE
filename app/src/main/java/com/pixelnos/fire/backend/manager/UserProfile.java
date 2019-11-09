package com.pixelnos.fire.backend.manager;

public class UserProfile {

    public ProfileData userData;

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
