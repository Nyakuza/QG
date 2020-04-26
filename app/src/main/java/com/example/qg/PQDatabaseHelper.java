package com.example.qg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PQDatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Personal_table";
    private static final String COL1 = "Quiz_name";
    private static final String COL2 = "Note";




    public PQDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL1 + " TEXT ," +
                COL2 + " TEXT) " ;

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete
    }
    public boolean addData(String qn, String n) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, qn);
        contentValues.put(COL2, n);

        //Log.d(TAG)
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getData(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return null;
        }
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = "+ Integer.toString(position);
        Cursor data = db.rawQuery(query,null);
        return data;
    }


}

