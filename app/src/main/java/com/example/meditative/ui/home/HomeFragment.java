package com.example.meditative.ui.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.meditative.HomeActivity;
import com.example.meditative.LoginActivity;
import com.example.meditative.R;
import com.example.meditative.ui.journal.AddJournal;
import com.example.meditative.ui.journal.JournalFragment;
import com.example.meditative.ui.music.MusicFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View root;
    private ViewGroup container;
    private Button newJournalBtn;
    private Button openJournalBtn;
    private Button settingsBtn;
    private Button openMusicBtn;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.container = container;
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        newJournalBtn = (Button) root.findViewById(R.id.newJournal);
        newJournalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddJournal.class);
                ((HomeActivity) getActivity()).startActivity(intent);
            }
        });
        openJournalBtn = (Button) root.findViewById(R.id.journalButton);
        openJournalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JournalFragment fragment2= new JournalFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFragment,fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        settingsBtn = (Button) root.findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                ((HomeActivity) getActivity()).startActivity(intent);
            }
        });
        openMusicBtn = (Button) root.findViewById(R.id.musicButton);
        openMusicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicFragment fragment2= new MusicFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.homeFragment,fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
    public void openMusic(View view)
    {

    }
}