package com.example.meditative.ui.journal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditative.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class JournalFragment extends Fragment {

    private JournalViewModel journalViewModel;
    FloatingActionButton addButton;
    RecyclerView recyclerView;
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        journalViewModel =
                new ViewModelProvider(this).get(JournalViewModel.class);
        root = inflater.inflate(R.layout.fragment_journal, container, false);
        addButton = (FloatingActionButton) root.findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) root.findViewById(R.id.listOfNotes);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getContext(), AddJournal.class);
                startActivity(i);
            }
        });



        return root;
    }
}