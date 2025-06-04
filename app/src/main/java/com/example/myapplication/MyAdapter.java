package com.example.myapplication;


import static androidx.core.content.ContextCompat.getDrawable;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    Context context;
    ArrayList<Notes> notes;

    Notes_recyclerviewInterface recyclerviewInterface;
    public MyAdapter(Context context, ArrayList<Notes> notes, Notes_recyclerviewInterface recyclerviewInterface){
        this.context = context;
        this.notes = notes;
        this.recyclerviewInterface = recyclerviewInterface;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //gets the note cards to be displayed in a list on screen
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notecard, parent, false);
        return new MyViewHolder(view, recyclerviewInterface).linkAdpater(this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        //adds dynamic to the names of the title, importance status of a tag + color
        holder.navn.setText(notes.get(position).tittel);
        holder.tag.setText(notes.get(position).tag);

        if(holder.tag.getText().equals("High")){
            holder.tag.setBackground(getDrawable(context, R.drawable.note_tag_high));
        } else if (holder.tag.getText().equals("Mid")) {
            holder.tag.setBackground(getDrawable(context, R.drawable.note_tag_mid));
        } else {
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
        private MyAdapter adapter;

        Data_Notes dataKilde;



        public MyViewHolder(@NonNull View itemView, Notes_recyclerviewInterface recyclerviewInterface) {
            super(itemView);
            navn = itemView.findViewById(R.id.tittel);
            tag = itemView.findViewById(R.id.tag);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerviewInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){

                            recyclerviewInterface.onItemClick(position);

                            // background for the safe guard button
                            // Button positive = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                            // positive.setBackground(getDrawable(adapter.context,R.drawable.defualt_priority_button));

                        }
                    }
                }
            });
        }

        public MyViewHolder linkAdpater(MyAdapter adapter) {
            this.adapter = adapter;
            return this;
        }


    }
}
