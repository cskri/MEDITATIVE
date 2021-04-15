package com.example.meditative.ui.journal;

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

public class JournalFragment extends Fragment {

    private JournalViewModel journalViewModel;
    Toolbar toolbar;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        journalViewModel =
                new ViewModelProvider(this).get(JournalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_journal, container, false);
        toolbar = root.findViewById(R.id.toolbar);
        recyclerView = root.findViewById(R.id.listOfNotes);



        return root;
    }
}