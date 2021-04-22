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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditative.Adapter;
import com.example.meditative.JournalDatabase;
import com.example.meditative.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class JournalFragment extends Fragment {

    private JournalViewModel journalViewModel;

    RecyclerView recyclerView;
    View root;
    Adapter adapter;
    List<Note> notes;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        journalViewModel =
                new ViewModelProvider(this).get(JournalViewModel.class);
        root = inflater.inflate(R.layout.fragment_journal, container, false);

        JournalDatabase db = new JournalDatabase(root.getContext());
        notes = db.getNotes();
        recyclerView = (RecyclerView) root.findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        adapter = new Adapter(root.getContext(), notes);
        recyclerView.setAdapter(adapter);


        return root;
    }
}