package com.example.meditative;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> runner(), SPLASH_SCREEN);



    }

    private void runner () {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        boolean hasLoggedIn = pref.getBoolean("hasLoggedIn", false);
        Intent homeIntent;
        if(hasLoggedIn) {
            homeIntent = new Intent(MainActivity.this, HomeActivity.class);
        }
        else{
            homeIntent = new Intent(MainActivity.this, LoginActivity.class);
        }
        startActivity(homeIntent);
        finish();
    }
}