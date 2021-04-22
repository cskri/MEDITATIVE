package com.example.meditative;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditative.ui.journal.Note;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    public Adapter(Context context, List<Note> notes){
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int i) {
        String date = notes.get(i).getDate();
        String time = notes.get(i).getTime();
        int mood = notes.get(i).getMood();
        holder.dateText.setText(date);
        holder.timeText.setText(time);
        if (mood == 1) holder.moodImage.setImageResource(R.drawable.ic_happy_24);
        else if (mood == 2) holder.moodImage.setImageResource(R.drawable.ic_neutral_24);
        else if (mood == 3) holder.moodImage.setImageResource(R.drawable.ic_sad_24);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateText, timeText;
        ImageView moodImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            timeText = itemView.findViewById(R.id.timeText);
            moodImage = itemView.findViewById(R.id.moodImage);
        }
    }
}
