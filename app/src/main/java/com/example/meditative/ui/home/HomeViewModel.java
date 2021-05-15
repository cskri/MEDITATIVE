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
            mText.setValue("What do you fancy this morning, " + pref.getString("UserName","User") + "?");
        }
        else if (date.getHours()<=17){
            mText.setValue("A tune to lighten your mood, " + pref.getString("UserName","User") + "?");
        }
        else if(date.getHours()<=24){
            mText.setValue("Tell me about your day, " + pref.getString("UserName","User"));
        }
    }



    public LiveData<String> getText() {
        return mText;
    }

}