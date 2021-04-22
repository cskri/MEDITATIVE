package com.example.meditative;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.meditative.ui.journal.Note;

import java.util.ArrayList;
import java.util.List;

public class JournalDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "journaldb";
    private static final String DATABASE_TABLE = "journals";

    // column names for DB table
    private static final String KEY_ID = "id";
    private static final String KEY_MOOD = "mood";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public JournalDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE nametable(id INT PRIMARY KEY, content TEXT, date TEXT, time TEXT, mood INT);
        String query = "CREATE TABLE " + DATABASE_TABLE + " ("+
                KEY_ID+" INTEGER PRIMARY KEY," +
                KEY_MOOD + " INTEGER,"+
                KEY_CONTENT + " TEXT,"+
                KEY_DATE + " TEXT,"+
                KEY_TIME + " TEXT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_MOOD, note.getMood());
        c.put(KEY_CONTENT,note.getContent());
        c.put(KEY_DATE, note.getDate());
        c.put(KEY_TIME, note.getTime());

        long ID = db.insert(DATABASE_TABLE, null,c);
        Log.d("Inserted in DB", "ID -> " + ID);
        return ID;
    }
    public Note getNote(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = new String[] {KEY_ID,KEY_MOOD,KEY_CONTENT,KEY_DATE,KEY_TIME};
        Cursor cursor = db.query(DATABASE_TABLE,query, KEY_ID+"=?", new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null) cursor.moveToFirst();

        return new Note(
                Long.parseLong(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }
    public List<Note> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNotes = new ArrayList<>();
        String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY " +KEY_ID+" DESC";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setID(Long.parseLong(String.valueOf(cursor.getLong(0))));
                note.setMood(Integer.parseInt(String.valueOf(cursor.getInt(1))));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                allNotes.add(note);
            }while(cursor.moveToNext());
        }
        return allNotes;
    }

}
