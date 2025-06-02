package com.example.myapplication;


import java.util.Collections;
import java.util.Comparator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Mynotes extends AppCompatActivity {

    private RecyclerView recyclerView;
    public ImageButton newnote;
    SharedPreferences sp;
    Button importanceLow;
    Button importanceMid;
    Button importanceHigh;
    Button cancelNote;
    Button makeNote;
    EditText notatTxt;

    CardView card;

    public Data_Notes dataKilde;
    private ArrayList<Notes> notesList;
    int tag =0;
    String tagnavn = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynotes);

        //adds ids to elements present in the my notes page
        recyclerView = findViewById(R.id.notes);
        newnote = findViewById(R.id.newnoteButton);
        
        // temperary storage of notes: next step is to make a notes object and store it using sqllite
        dataKilde = new Data_Notes(this);
        dataKilde.open();
        notesList = dataKilde.finnAlleNotater();
        System.out.println(notesList);

        // uses collections to sort the data using the tagId in order High -> Low. (Will add options for sorting)
        notesList.sort(new arraySort());

        // Stores the amount of notes to be displayed to the main page
        sp = getSharedPreferences("listcount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count",notesList.size());
        editor.apply();






        //Prompt to add info for notes
        newnote.setOnClickListener(View -> {

            LayoutInflater inflater = this.getLayoutInflater();
            View viewNoteBox = inflater.inflate(R.layout.newnote_dialogbox, null);

            // 0 -> 2 // 0 = low // 1 = mid // 2 = high
            notatTxt = viewNoteBox.findViewById(R.id.notatTxt);
            cancelNote = viewNoteBox.findViewById(R.id.cancelnoteButton);
            makeNote = viewNoteBox.findViewById(R.id.makenoteButton);


            // initiating buttons in dialouge prompt
            importanceLow = viewNoteBox.findViewById(R.id.importanceLowButton);
            importanceMid = viewNoteBox.findViewById(R.id.importanceMidButton);
            importanceHigh = viewNoteBox.findViewById(R.id.importanceHighButton);

            //importanceLow.setBackground(getDrawable(R.drawable.newnotebutton));
            importanceLow.setBackgroundColor(getResources().getColor(R.color.black));
            importanceMid.setBackgroundColor(getResources().getColor(R.color.black));
            importanceHigh.setBackgroundColor(getResources().getColor(R.color.black));


            // change color for low when selected
            importanceLow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    importanceLow.setBackgroundColor(getResources().getColor(R.color.green));
                    importanceMid.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceHigh.setBackgroundColor(getResources().getColor(R.color.black));
                    tag=0;
                }
            });

            // change color for mid when selected
            importanceMid.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    importanceLow.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceMid.setBackgroundColor(getResources().getColor(R.color.yellow));
                    importanceHigh.setBackgroundColor(getResources().getColor(R.color.black));
                    tag = 1;

                }
            });

            // change color for high when selected
            importanceHigh.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    importanceLow.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceMid.setBackgroundColor(getResources().getColor(R.color.black));
                    importanceHigh.setBackgroundColor(getResources().getColor(R.color.red));
                    tag=2;


                }
            });

            // shows the prompt box with inputs
            AlertDialog.Builder builder = new AlertDialog.Builder(Mynotes.this);
            builder.setView(viewNoteBox)
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

            // Takes the data from the prompt and creates a new note.
            makeNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tagId = String.valueOf(tag);

                    if(tagId.equals("2")){
                        tagnavn = "High";
                    } else if (tagId.equals("1")) {
                        tagnavn="Mid";
                    } else {
                        tagnavn = "Low";
                    }

                    System.out.println(tagnavn);
                    System.out.println();
                    dataKilde.leggInnNotes("Note", notatTxt.getText().toString(),tagnavn,tagId);
                    finish();
                }
            });

        });

		// makes is possible to view the list of notes
        MyAdapter myAdapter = new MyAdapter(this, notesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        card = recyclerView.findViewById(R.id.card);

    }

    // function that sorts array of notes (will add different methods to sort it)
    public class arraySort implements Comparator<Notes> {
        public int compare(Notes o1, Notes o2) {
            return o2.tagId.compareTo(o1.tagId);

        }
    }


}
