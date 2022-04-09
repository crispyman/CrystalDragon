package com.teamsatisfaction.crystaldragon;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATA_TABLE = "DATA_TABLE";
    public static final String COL_STRING_DATA = "STRING_DATA";
    public static final String COL_INT_DATA = "INT_DATA";
    public static final String COL_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "database.db",null,1);
    }

    // This is called the first time a datbase is accessed, creates a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + DATA_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_STRING_DATA + " TEXT, " + COL_INT_DATA + " INT)";

        db.execSQL(createTableStatement);

    }

    // This is alled if the database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(database database) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_STRING_DATA, database.getDataPoint01());
        cv.put(COL_INT_DATA, database.getDataPoint02());

        long insert = db.insert(DATA_TABLE, null , cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
}
