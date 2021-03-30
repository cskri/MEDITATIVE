package com.example.meditative;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.meditative.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    Date date = Calendar.getInstance().getTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView textView = (TextView) findViewById(R.id.nameView);
        SharedPreferences pref = getApplication().getSharedPreferences("MyPref", 0);
        if(date.getHours()<=12) {
            textView.setText("Goodmorning " + pref.getString("UserName","User"));
        }
        else if (date.getHours()<=17){
            textView.setText("Good Afternoon " + pref.getString("UserName","User"));
        }
        else if(date.getHours()<=24){
            textView.setText("Good Evening " + pref.getString("UserName","User"));
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_journal)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}