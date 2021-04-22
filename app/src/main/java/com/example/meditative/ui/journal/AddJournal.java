package com.example.meditative.ui.journal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toolbar;

import com.example.meditative.JournalDatabase;
import com.example.meditative.R;
import com.google.android.material.chip.Chip;

import java.util.Calendar;

public class AddJournal extends AppCompatActivity {

    Switch happy, neutral, sad;
    EditText noteContent;
    Calendar c;
    String todaysDate;
    String currentTime;
    int mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);
        noteContent = (EditText) findViewById(R.id.editTextTextMultiLine);
        happy = (Switch) findViewById(R.id.happy);
        neutral = (Switch) findViewById(R.id.neutral);
        sad = (Switch) findViewById(R.id.sad);
        happy.setChecked(true);

        //get current date and time
        c = Calendar.getInstance();

        todaysDate = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
    }

    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

    public void happySwitch(View view) {
        if(!happy.isChecked() && !neutral.isChecked() && !sad.isChecked()){
            happy.setChecked(true);
        }
        mood = 1;
        neutral.setChecked(false);
        sad.setChecked(false);
    }

    public void neutralSwitch(View view) {
        if(!happy.isChecked() && !neutral.isChecked() && !sad.isChecked()){
            neutral.setChecked(true);
        }
        mood = 2;
        happy.setChecked(false);
        sad.setChecked(false);
    }

    public void sadSwitch(View view) {
        if(!happy.isChecked() && !neutral.isChecked() && !sad.isChecked()){
            sad.setChecked(true);
        }
        mood = 3;
        neutral.setChecked(false);
        happy.setChecked(false);
    }

    public void backButton(View view) {
        onBackPressed();
    }

    public void saveButton(View view) {
        Note note = new Note(mood, noteContent.getText().toString(), todaysDate, currentTime);
        JournalDatabase db = new JournalDatabase(this);
        db.addNote(note);
        onBackPressed();
    }
}