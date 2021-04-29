package com.example.meditative.ui.journal;

import android.content.Intent;
import android.os.Bundle;

import com.example.meditative.JournalDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditative.R;

public class Details extends AppCompatActivity {
    TextView mDetails;
    ImageView moodImage;
    JournalDatabase db;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDetails = findViewById(R.id.detailsOfNote);
        moodImage = findViewById(R.id.imageView);

        Intent i = getIntent();
        id = i.getLongExtra("ID",0);

        db = new JournalDatabase(this);
        Note note = db.getNote(id);
        getSupportActionBar().setTitle(note.getDate() + " " + note.getTime());
        mDetails.setText(note.getContent());

        if (note.getMood() == 1) moodImage.setImageResource(R.drawable.ic_happy_24);
        else if (note.getMood() == 2) moodImage.setImageResource(R.drawable.ic_neutral_24);
        else if (note.getMood() == 3) moodImage.setImageResource(R.drawable.ic_sad_24);
    }
    public void backButton(View view) {
        onBackPressed();
    }
    public void deleteButton(View view){
        db.deleteNote(id);
        Toast.makeText(getApplicationContext(), "Journal Note deleted", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}