package com.example.meditative.ui.home;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    Date date = Calendar.getInstance().getTime();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        SharedPreferences pref = getApplication().getSharedPreferences("MyPref", 0);
        if(date.getHours()<=12) {
            mText.setValue("Goodmorning " + pref.getString("UserName","User") + ", fancy some morning tunes?");
        }
        else if (date.getHours()<=17){
            mText.setValue("Good Afternoon " + pref.getString("UserName","User") + ", how is it going so far?");
        }
        else if(date.getHours()<=24){
            mText.setValue("Good Evening " + pref.getString("UserName","User") + ", how has your day been?");
        }
    }



    public LiveData<String> getText() {
        return mText;
    }

}