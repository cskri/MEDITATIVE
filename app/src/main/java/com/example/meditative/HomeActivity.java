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

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.meditative.ui.home.HomeViewModel;
import com.example.meditative.ui.journal.AddJournal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    Date date = Calendar.getInstance().getTime();
    Boolean playerInitiated = false;
    ImageButton playPause, addNote;
    MediaPlayer mp;
    TextView musicLabel;
    ProgressBar progress;
    TextView durationLabel;
    int audioDuration;
    static HomeActivity instance;
    static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        active = true;
        instance = this;
        musicLabel = (TextView) findViewById(R.id.songTextLabel);
        durationLabel = (TextView) findViewById(R.id.durationLabel);
        progress = findViewById(R.id.progressBar);
        final TextView textView = (TextView) findViewById(R.id.nameView);
        playPause=(ImageButton) findViewById(R.id.playbutton);
        playPause.setEnabled(false);
        addNote = (ImageButton) findViewById(R.id.imageButton);
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
    public static HomeActivity getInstance()
    {
        return instance;
    }

    public void newNote(View view){
        Intent i = new Intent(this, AddJournal.class);
        startActivity(i);
    }
    public void play(View view){
        if(!playerInitiated){
            playPause.setEnabled(false);
            try{
                mp.setOnPreparedListener(this);
                mp.setOnErrorListener(this);
                mp.prepareAsync();
                audioDuration = mp.getDuration();
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
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            progress.setProgress(0);
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                } catch (Exception e) {
                    return;
                }
                if(mp.isPlaying()) {
                    setPosition();
                    durationLabel.post(new Runnable(){
                        public void run() {
                            setDuration();
                        }
                    });
                }
            }
        }

    });

    public void setPosition()
    {
        progress.setProgress(mp.getCurrentPosition());
    }
    public void setDuration()
    {
        String duration = convertDurationMillis(mp.getCurrentPosition()) + "/" + convertDurationMillis(mp.getDuration());
        durationLabel.setText(duration);
    }
    public static String convertDurationMillis(long milliseconds){
        String secondsString;
        //Convert total duration into time
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Pre appending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        // return timer string
        return minutes + ":" + secondsString;
    }
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
            mp.seekTo(0);
            mp.start();
            playPause.setEnabled(true);
            playPause.setBackgroundResource(R.drawable.ic_pause_24);
            playerInitiated = true;
            progress.setMax(mp.getDuration());
            if(!t.isAlive()){ t.start();}

    }
    public MediaPlayer getMp(){
        return mp;
    }
    public TextView getMusicLabel(){
        return musicLabel;
    }
    public void playNewSong(String dataSource, View v)
    {
        try {
            playerInitiated = false;
            mp.stop();
            mp.reset();
            mp.setDataSource(dataSource);
            play(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }
}