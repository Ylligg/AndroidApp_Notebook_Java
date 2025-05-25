package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Mynotes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynotes);

        recyclerView = findViewById(R.id.notes);
        arrayList = new ArrayList<String>();

        arrayList.add("Hei1");
        arrayList.add("Hei2");
        arrayList.add("Hei3");
        arrayList.add("Hei");
        arrayList.add("Hei");



        MyAdapter myAdapter = new MyAdapter(this, arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }

    public void additem(View view){
        arrayList.add("Hei");
    }
}
