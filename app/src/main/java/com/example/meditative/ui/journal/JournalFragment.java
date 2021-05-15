package com.example.meditative.ui.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditative.R;

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