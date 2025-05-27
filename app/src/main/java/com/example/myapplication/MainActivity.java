package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  	public ImageButton notebook;

    public TextView counttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        notebook = findViewById(R.id.notebookButton);


        counttxt = findViewById(R.id.counttxt);


        SharedPreferences sp = getApplicationContext().getSharedPreferences("listcount", Context.MODE_PRIVATE);

        int count = sp.getInt("count", 0);

        String countString = String.valueOf(count);

		counttxt.setText(countString);

        notebook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               openNotes();

            }
        });




    }

    public void openNotes(){
        Intent intent = new Intent(this, Mynotes.class);
        startActivity(intent);
    }






}