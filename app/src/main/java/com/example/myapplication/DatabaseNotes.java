package com.example.myapplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// oppretter database for venner
public class DatabaseNotes extends SQLiteOpenHelper {
    private static final String DATABASE_NAVN = "notes.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABELL_NOTES = "notes";
    public static final String KOLONNE_ID = "id";
    public static final String KOLONNE_NOTE_TITTEL = "tittel";
    public static final String KOLONNE_NOTE_NOTAT = "notat";

    public static final String KOLONNE_NOTE_TAG = "tag";

    public static final String KOLONNE_NOTE_TAGID = "tagid";
    private static final String CREATE_TABLE_NOTES = "CREATE TABLE " +
            TABELL_NOTES +
            "(" + KOLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KOLONNE_NOTE_TITTEL + " TEXT NOT NULL, " +
            KOLONNE_NOTE_NOTAT + " TEXT NOT NULL," +
            KOLONNE_NOTE_TAG + " TEXT NOT NULL," +
            KOLONNE_NOTE_TAGID + " TEXT NOT NULL)";
    public DatabaseNotes(Context context) {
        super(context, DATABASE_NAVN, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    { onCreate(db);
    }
}
