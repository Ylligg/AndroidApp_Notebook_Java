package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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


        newnote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addNote();

            }
        });


		// makes is possible to view the list of notes
        MyAdapter myAdapter = new MyAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }

    public void addNote(){
        Toast.makeText(Mynotes.this, "Lagt inn ny notat", Toast.LENGTH_LONG).show();
    }


}
