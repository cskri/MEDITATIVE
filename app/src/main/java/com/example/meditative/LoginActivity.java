package com.example.meditative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private static LoginActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void login(View view) {
        EditText username = (EditText) findViewById(R.id.editText1);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("UserName", username.getText().toString());
        editor.putBoolean("hasLoggedIn", true);
        editor.commit();
        if(HomeActivity.active)
        {
            onBackPressed();
        }
        else
        {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            this.finish();
        }

    }

}