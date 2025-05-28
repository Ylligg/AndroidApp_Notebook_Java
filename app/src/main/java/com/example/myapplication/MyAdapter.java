package com.example.myapplication;


import static androidx.core.content.ContextCompat.getDrawable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;
    ArrayList<Notes> notes;


    public MyAdapter(Context context, ArrayList<Notes> notes){
        this.context = context;
        this.notes = notes;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notecard, parent, false);



        return new MyViewHolder(view).linkAdpater(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.navn.setText(notes.get(position).tittel);
        holder.tag.setText(notes.get(position).tag);

        if(holder.tag.getText().equals("High")){
            holder.tag.setBackground(getDrawable(context, R.drawable.note_tag_high));
        } else if (holder.tag.getText().equals("Mid")) {
            holder.tag.setBackground(getDrawable(context, R.drawable.note_tag_mid));
        }
        else {
            holder.tag.setBackground(getDrawable(context, R.drawable.note_tag_low));
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView navn;
        TextView tag;
        ImageButton newnote;
        private MyAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            navn = itemView.findViewById(R.id.tittel);
            tag = itemView.findViewById(R.id.tag);




        }

        public MyViewHolder linkAdpater(MyAdapter adapter) {
            this.adapter = adapter;
            return this;
        }


    }
}
