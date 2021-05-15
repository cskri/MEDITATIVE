package com.example.meditative.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditative.HomeActivity;
import com.example.meditative.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MusicFragment extends Fragment {

    private MusicViewModel musicViewModel;
    private RecyclerView mFirestoreList;
    private FirebaseFirestore db;
    private TextView musicLabel;
    FirestoreRecyclerAdapter adapter;
    private HomeActivity homeActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_music, container, false);
        db = FirebaseFirestore.getInstance();
        mFirestoreList = root.findViewById(R.id.firestore_list);
        homeActivity = HomeActivity.getInstance();
        musicLabel = homeActivity.getMusicLabel();
        //Query
        Query query = db.collection("Music").orderBy("Name");
        //RecyclerOptions
        FirestoreRecyclerOptions<MusicModel> options = new FirestoreRecyclerOptions.Builder<MusicModel>().setQuery(query,MusicModel.class).build();
        adapter = new FirestoreRecyclerAdapter<MusicModel, MusicViewHolder>(options) {
            @NonNull
            @Override
            public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);
                return new MusicViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(@NonNull MusicViewHolder holder, int position, @NonNull MusicModel model)
            {
                holder.list_name.setText(model.getName());
                holder.list_artist.setText(model.getArtist());
                holder.list_button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)
                    {
                        homeActivity.playNewSong(model.getURL(),v);
                        musicLabel.setText(model.getName() + "  -  " + model.getArtist());
                    }
                });
            }
        };
        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mFirestoreList.setAdapter(adapter);

        return root;
    }

    private class MusicViewHolder extends RecyclerView.ViewHolder{

        private TextView list_name;
        private TextView list_artist;
        private Button list_button;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_name);
            list_artist = itemView.findViewById(R.id.list_artist);
            list_button = itemView.findViewById(R.id.list_button);

        }
    }
    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
}