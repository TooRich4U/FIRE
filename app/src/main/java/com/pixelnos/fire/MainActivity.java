package com.pixelnos.fire;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pixelnos.fire.backend.manager.Account;
import com.pixelnos.fire.backend.manager.Currency;
import com.pixelnos.fire.backend.manager.Transaction;
import com.pixelnos.fire.backend.manager.UserProfile;
import com.pixelnos.fire.backend.manager.Wallet;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //Dummy
    public static UserProfile.ProfileData profileData = new UserProfile.ProfileData();
    public static Wallet wallet = new Wallet();
    public static UserProfile user = new UserProfile(profileData, wallet);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        createDummyAccount();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }
    public void createDummyAccount(){
        Currency currency = new Currency("Euros", "EUR", "€", 1.10);
        for(int i = 1; i < 15;i++) {
            Account account = new Account("Account "+ i, currency, 10000*i);
            Transaction transaction = new Transaction(1000,"Mars",new Date(),new ArrayList<String>());
            account.addDebit(transaction);
            user.getWallet().add(account);
        }
    }

}
