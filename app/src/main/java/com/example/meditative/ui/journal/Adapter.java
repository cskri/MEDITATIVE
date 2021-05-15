package com.example.meditative.ui.journal;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meditative.R;

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
        long id = notes.get(i).getID();
        holder.dateText.setText(date);
        holder.timeText.setText(time);
        if (mood == 1){
            holder.moodImage.setImageResource(R.drawable.ic_happy_24);
            ImageViewCompat.setImageTintList(holder.moodImage, ColorStateList.valueOf(Color.parseColor("#FF39AE1F")));
        }
        else if (mood == 2){
            holder.moodImage.setImageResource(R.drawable.ic_neutral_24);
            ImageViewCompat.setImageTintList(holder.moodImage, ColorStateList.valueOf(Color.parseColor("#FFECD92B")));
        }
        else if (mood == 3){
            holder.moodImage.setImageResource(R.drawable.ic_sad_24);
            ImageViewCompat.setImageTintList(holder.moodImage, ColorStateList.valueOf(Color.parseColor("#FFCD3D3D")));
        }

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

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                  Intent i = new Intent(v.getContext(), Details.class);
                  i.putExtra("ID",notes.get(getAdapterPosition()).getID());
                  v.getContext().startActivity(i);
                }
            });
        }
    }
}
