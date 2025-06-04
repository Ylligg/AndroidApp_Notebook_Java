package com.example.myapplication;

import static androidx.core.content.ContextCompat.getDrawable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowNotes extends AppCompatActivity implements Notes_recyclerviewInterface {

    Data_Notes dataKilde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shownotes);
        long id = getIntent().getLongExtra("ID",0);
        String tittel = getIntent().getStringExtra("Tittel");
        String note = getIntent().getStringExtra("Notat");
        String tag = getIntent().getStringExtra("Tag");
        String tagid = getIntent().getStringExtra("TagID");


        EditText tittelTextview = findViewById(R.id.TittelNotetxt);
        EditText noteTextview = findViewById(R.id.NoteNotetxt);
        TextView tagTextview = findViewById(R.id.TagNote);
        Button updatebutton = findViewById(R.id.NoteUpdateButton);

        if(tag.equals("High")){
            tagTextview.setBackground(getDrawable(R.drawable.note_tag_high));
        } else if (tag.equals("Mid")) {
            tagTextview.setBackground(getDrawable(R.drawable.note_tag_mid));
        } else {
            tagTextview.setBackground(getDrawable(R.drawable.note_tag_low));
        }

        tittelTextview.setText(tittel);
        noteTextview.setText(note);
        tagTextview.setText(tag);
        updatebutton.setEnabled(false);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                updatebutton.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updatebutton.setBackground(getDrawable(R.drawable.updatenote_buttoncolor));
            }
        };

        tittelTextview.addTextChangedListener(tw);
        noteTextview.addTextChangedListener(tw);

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updatebutton.isEnabled()){
                    dataKilde = new Data_Notes(ShowNotes.this);
                    dataKilde.open();
                    dataKilde.updateNotat(id, tittelTextview.getText().toString(),noteTextview.getText().toString(), tag, tagid);
                    Toast.makeText(ShowNotes.this, "Endret note", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ShowNotes.this, Mynotes.class);
                    startActivity(intent);


                }

            }
        });

    }



    @Override
    public void onItemClick(int position) {

    }
}