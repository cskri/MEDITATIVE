package com.example.meditative;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JournalDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "journaldb";
    private static final String DATABASE_TABLE = "journals";

    // column names for DB table
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_MOOD = "mood";

    JournalDatabase (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE nametable(id INT PRIMARY KEY, content TEXT, date TEXT, time TEXT, mood INT);
        String query = "CREATE TABLE " + DATABASE_TABLE + " ("+ KEY_ID+" INT PRIMARY KEY," +
                KEY_CONTENT + " TEXT,"+
                KEY_DATE + " TEXT,"+
                KEY_TIME + " TEXT,"+
                KEY_DATE + " TEXT,"+
                KEY_MOOD + " INT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}
