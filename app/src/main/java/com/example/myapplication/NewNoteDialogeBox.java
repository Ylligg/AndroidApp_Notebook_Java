package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewNoteDialogeBox extends RecyclerView.Adapter<NewNoteDialogeBox.MyViewHolder>{
    Context context;


    public NewNoteDialogeBox(Context context){
        this.context = context;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.newnote_dialogbox, parent, false);

        return new MyViewHolder(view).linkAdpater(this);
    }

    @Override
    public void onBindViewHolder(@NonNull NewNoteDialogeBox.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        Button importanceLow;
        Button importanceMid;
        Button importanceHigh;
        private NewNoteDialogeBox adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public MyViewHolder linkAdpater(NewNoteDialogeBox adapter) {
            this.adapter = adapter;
            return this;
        }


    }
}
