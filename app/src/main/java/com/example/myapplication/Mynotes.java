package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import static androidx.core.content.ContextCompat.getDrawable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Mynotes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Notes> arrayList;

    Context context;

    public ImageButton newnote;
    SharedPreferences sp;

    Button importanceLow;
    Button importanceMid;
    Button importanceHigh;
    public TextView tagbutton;

    int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynotes);

        recyclerView = findViewById(R.id.notes);
        newnote = findViewById(R.id.newnoteButton);




        // temperary storage of notes: next step is to make a notes object and store it using sqllite
        arrayList = new ArrayList<Notes>();

        arrayList.add(new Notes(0, "Nei", "Hei","Low"));
        arrayList.add(new Notes(0, "Hei", "Hei","High"));
        arrayList.add(new Notes(0, "Nei", "Hei","Mid"));
        arrayList.add(new Notes(0, "Nei", "Hei","Mid"));
        arrayList.add(new Notes(0, "Nei", "Hei","Mid"));



        LayoutInflater inflater2 = this.getLayoutInflater();
        View viewTag = inflater2.inflate(R.layout.notecard, null);
        tagbutton = viewTag.findViewById(R.id.tag);

        /*
        for(Notes i : arrayList){
            if(i != null) {

                if (i.tag.equals("High")) {
                    tagbutton.setBackground(getDrawable(R.drawable.note_tag_high));
                } else if (i.tag.equals("Mid")) {
                    tagbutton.setBackground(getDrawable(R.drawable.note_tag_mid));
                } else {
                    tagbutton.setBackground(getDrawable(R.drawable.note_tag_low));
                }


            }
        }

         */




        // Stores the amount of notes to be displayed to the main page
        sp = getSharedPreferences("listcount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count",arrayList.size());
        editor.apply();

        //Prompt to add info for notes
        newnote.setOnClickListener(View -> {

            LayoutInflater inflater = this.getLayoutInflater();
            View viewNoteBox = inflater.inflate(R.layout.newnote_dialogbox, null);

            // 0 -> 2 // 0 = low // 1 = mid // 2 = high
            tag =0;




            importanceLow = viewNoteBox.findViewById(R.id.importanceLowButton);
            importanceMid = viewNoteBox.findViewById(R.id.importanceMidButton);
            importanceHigh = viewNoteBox.findViewById(R.id.importanceHighButton);

            //importanceLow.setBackground(getDrawable(R.drawable.newnotebutton));
            importanceLow.setBackgroundColor(getResources().getColor(R.color.black));
            importanceMid.setBackgroundColor(getResources().getColor(R.color.black));
            importanceHigh.setBackgroundColor(getResources().getColor(R.color.black));


            // change color for low
            importanceLow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    importanceLow.setBackgroundColor(getResources().getColor(R.color.green));
                    importanceMid.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceHigh.setBackgroundColor(getResources().getColor(R.color.black));
                    tag=0;
                    System.out.println(tag);

                }
            });

            // change color for mid
            importanceMid.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    importanceLow.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceMid.setBackgroundColor(getResources().getColor(R.color.yellow));
                    importanceHigh.setBackgroundColor(getResources().getColor(R.color.black));
                    tag = 1;
                    System.out.println(tag);

                }
            });

            // change color for high
            importanceHigh.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    importanceLow.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceMid.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceHigh.setBackgroundColor(getResources().getColor(R.color.red));
                    tag=2;
                    System.out.println(tag);

                }
            });


            AlertDialog.Builder builder = new AlertDialog.Builder(Mynotes.this);

            builder
                    .setView(viewNoteBox)
                    /*
                    .setPositiveButton("Fullfør", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setNegativeButton("Avslutt", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    */

                    .show();

        });



		// makes is possible to view the list of notes
        MyAdapter myAdapter = new MyAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }


}
