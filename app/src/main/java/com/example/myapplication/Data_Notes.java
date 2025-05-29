package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Data_Notes {
    private SQLiteDatabase database;
    private DatabaseNotes dbHelper;

    public Data_Notes(Context context) {
        dbHelper = new DatabaseNotes(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // metode som legger til venn i db
    public Notes leggInnNotes(String tittel, String notat, String tag, String tagid) {
        ContentValues values = new ContentValues();
        values.put(DatabaseNotes.KOLONNE_NOTE_TITTEL, tittel);
        values.put(DatabaseNotes.KOLONNE_NOTE_NOTAT, notat);
        values.put(DatabaseNotes.KOLONNE_NOTE_TAG, tag);
        values.put(DatabaseNotes.KOLONNE_NOTE_TAGID, tagid);
        long insertId = database.insert(DatabaseNotes.TABELL_NOTES, null,
                values);
        Cursor cursor = database.query(DatabaseNotes.TABELL_NOTES, null,
                DatabaseNotes.KOLONNE_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Notes nyNote = cursorTilNotat(cursor);
        cursor.close();
        return nyNote;
    }

    private static Notes cursorTilNotat(Cursor cursor) {
        Notes note = new Notes();
        note.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseNotes.KOLONNE_ID)));
        note.setTittel(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotes.KOLONNE_NOTE_TITTEL)));
        note.setNotat(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotes.KOLONNE_NOTE_NOTAT)));
        note.setTag(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotes.KOLONNE_NOTE_TAG)));
        note.setTagId(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseNotes.KOLONNE_NOTE_TAGID)));
        return note;
    }

    // metode som henter alle venner fra db
    public ArrayList<Notes> finnAlleVenner() {
        ArrayList<Notes> notes = new ArrayList<>();
        Cursor cursor = database.query(DatabaseNotes.TABELL_NOTES, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Notes note = cursorTilNotat(cursor);
                notes.add(note);

            cursor.moveToNext();
        }
        cursor.close();
        return notes;
    }

    // metode som oppdaterer venn fra db
    public void updateNotat(long id, String nyTittel, String nyNotat, String nyTag, String nyTagid) {
        ContentValues values = new ContentValues();
        values.put(DatabaseNotes.KOLONNE_NOTE_TITTEL, nyTittel);
        values.put(DatabaseNotes.KOLONNE_NOTE_NOTAT, nyNotat);
        values.put(DatabaseNotes.KOLONNE_NOTE_TAG, nyTag);
        values.put(DatabaseNotes.KOLONNE_NOTE_TAGID, nyTagid);
        database.update(DatabaseNotes.TABELL_NOTES, values,
                DatabaseNotes.KOLONNE_ID + " =? ", new
                        String[]{Long.toString(id)});

    }

    // metode som sletter venn fra db
    public void slettNotat(long id) {

            database.delete(DatabaseNotes.TABELL_NOTES,
                    DatabaseNotes.KOLONNE_ID + " =? ", new
                            String[]{Long.toString(id)});

    }
}

