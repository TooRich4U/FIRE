package com.pixelnos.fire;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.pixelnos.fire.ui.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        final AppCompatActivity app = this;
        new CountDownTimer(3000, 1000) {
            public void onFinish() {
                Intent menuIntent = new Intent(app, MainActivity.class);
                startActivity(menuIntent);
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();
    }
}
