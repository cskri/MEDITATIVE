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

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.meditative.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    Date date = Calendar.getInstance().getTime();
    Boolean playerInitiated = false;
    ImageButton playPause;
    MediaPlayer mp;
    TextView musicLabel;
    String filepath = "C:\\Users\\caspe\\AndroidStudioProjects\\MEDITATIVE\\app\\src\\main\\res\\raw\\countdown_with_voice_and_sound_effects.mp3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        musicLabel = (TextView) findViewById(R.id.songTextLabel);
        final TextView textView = (TextView) findViewById(R.id.nameView);
        playPause=(ImageButton) findViewById(R.id.playbutton);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mp = new MediaPlayer();
        mp.setAudioAttributes(new AudioAttributes.Builder()
                .setFlags(AudioAttributes.FLAG_HW_AV_SYNC)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());

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

    public void play(View view){
        if(!playerInitiated){
            playPause.setEnabled(false);
            try{
                mp.setDataSource("https://www.bensound.com/bensound-music/bensound-relaxing.mp3");
                mp.prepareAsync();
                mp.setOnPreparedListener(this);
                mp.setOnErrorListener(this);
            }catch(Exception e){e.printStackTrace();}
        }
        else if(mp.isPlaying()){
            mp.pause();
            playPause.setBackgroundResource(R.drawable.ic_play_arrow_24);
        }
        else if(!mp.isPlaying()){
            mp.start();
            playPause.setBackgroundResource(R.drawable.ic_pause_24);
        }

    }
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
            mp.seekTo(0);
            mp.start();
            playPause.setEnabled(true);
            playPause.setBackgroundResource(R.drawable.ic_pause_24);
            playerInitiated = true;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}