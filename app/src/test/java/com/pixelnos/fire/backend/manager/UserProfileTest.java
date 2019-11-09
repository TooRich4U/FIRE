package com.pixelnos.fire.backend.manager;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserProfileTest {
    @Test
    public void createUser() {
        String job = "jobless";
        int age = 25;
        double netMonthlySalary = 250000;
        double savings = 8000;
        String currency = "EUR";
        UserProfile.ProfileData userData = new UserProfile.ProfileData();
        userData._age = age;
        userData._job = job;
        userData._salary = netMonthlySalary;
        userData._savings = savings;
        userData._currency = currency;
        userData._passiveIncomeTarget = 5000;
        userData._passiveIncomeStart = 45;
        userData._fireTarget = 0.50;
        UserProfile user = new UserProfile(userData);
        assertEquals(user.userData, userData);

    }
}
