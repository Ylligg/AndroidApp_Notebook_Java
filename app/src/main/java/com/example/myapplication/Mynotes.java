package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Mynotes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;

    Context context;

    public ImageButton newnote;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynotes);

        recyclerView = findViewById(R.id.notes);

        newnote = findViewById(R.id.newnoteButton);

        // temperary storage of notes: next step is to make a notes object and store it using sqllite
        arrayList = new ArrayList<String>();
        arrayList.add("Hei1");
        arrayList.add("Hei2");
        arrayList.add("Hei3");
        arrayList.add("Hei");
        arrayList.add("Hei");
        arrayList.add("Hei");
        arrayList.add("Hei");
        arrayList.add("Hei");
        arrayList.add("Hei");
        arrayList.add("Hei");
        arrayList.add("Hei");

        // Stores the amount of notes to be displayed to the main page
        sp = getSharedPreferences("listcount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count",arrayList.size());
        editor.apply();


        //Prompt to add info for notes
        newnote.setOnClickListener(View -> {

            LayoutInflater inflater = this.getLayoutInflater();
            View viewNoteBox = inflater.inflate(R.layout.newnote_dialogbox, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(Mynotes.this);

            builder
                    .setView(viewNoteBox)
                    .setCancelable(false)
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
